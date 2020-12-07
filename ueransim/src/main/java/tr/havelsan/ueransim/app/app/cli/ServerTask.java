/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.cli;

import tr.havelsan.ueransim.app.common.itms.*;
import tr.havelsan.ueransim.itms.nts.NtsTask;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;
import tr.havelsan.ueransim.utils.console.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class ServerTask extends NtsTask {

    private static final int RECEIVER_BUFFER_SIZE = 8 * 1024;
    private static final int CYCLE_TYPE_HEARTBEAT = 1;
    private static final int HEARTBEAT_PERIOD = 8000;

    private final CliTask cliTask;
    private final SocketList sockets;

    public ServerTask(CliTask cliTask) {
        super(true);
        this.cliTask = cliTask;
        this.sockets = new SocketList();
    }

    private static void acceptorThread(NtsTask task) {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(49973);
        } catch (IOException e) {
            Log.error(Tag.CLI, "TCP CLI server failure: " + e.getMessage());
            return;
        }

        while (true) {
            Socket socket;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                Log.error(Tag.CLI, "TCP CLI accept failure: " + e.getMessage());
                continue;
            }

            InputStream input;
            OutputStream output;

            try {
                input = socket.getInputStream();
            } catch (IOException ignored) {
                return;
            }

            try {
                output = socket.getOutputStream();
            } catch (IOException ignored1) {
                try {
                    input.close();
                } catch (IOException ignored2) {
                    return;
                }
                return;
            }

            task.push(new IwCliSocketAccept(socket, input, output));
        }
    }

    private static void clientThread(NtsTask task, Socket socket, InputStream input) {
        final int STATE_FIRST_LENGTH = 0;
        final int STATE_SECOND_LENGTH = 1;
        final int STATE_DATA = 2;

        var buffer = new byte[RECEIVER_BUFFER_SIZE];
        int offset = 0, length = 0;
        int state = STATE_FIRST_LENGTH;

        while (true) {
            if (!socket.isConnected() || socket.isClosed() || socket.isInputShutdown() || socket.isOutputShutdown())
                break;

            int read;
            try {
                read = input.read();
            } catch (IOException e) {
                break;
            }
            if (read < 0)
                break;

            if (state == STATE_FIRST_LENGTH) {
                length = read;
                state = STATE_SECOND_LENGTH;
            } else if (state == STATE_SECOND_LENGTH) {
                length <<= 8;
                length |= read;
                state = STATE_DATA;
            } else {
                if (length > RECEIVER_BUFFER_SIZE) {
                    break;
                }

                for (int i = 0; i < length; i++) {
                    int r;
                    try {
                        r = input.read();
                    } catch (IOException e) {
                        break;
                    }
                    if (r < 0)
                        break;
                    buffer[offset++] = (byte) r;
                }

                offset = 0;
                state = STATE_FIRST_LENGTH;

                var data = new byte[length];
                System.arraycopy(buffer, 0, data, 0, length);

                task.push(new IwCliServerReceive(socket, data));
            }
        }

        task.push(new IwCliSocketClose(socket));
    }

    @Override
    public void main() {
        Log.registerLogger(getThread(), Logger.GLOBAL);

        var acceptorThread = new Thread(() -> acceptorThread(this));
        Log.registerLogger(acceptorThread, Log.getLoggerOrDefault(getThread()));
        acceptorThread.start();

        pushDelayed(new IwPerformCycle(CYCLE_TYPE_HEARTBEAT), HEARTBEAT_PERIOD);

        while (true) {
            var msg = take();
            if (msg instanceof IwCliSocketAccept) {
                var socket = ((IwCliSocketAccept) msg).socket;
                var input = ((IwCliSocketAccept) msg).inputStream;
                var output = ((IwCliSocketAccept) msg).outputStream;

                sockets.add(socket, input, output);

                var clientThread = new Thread(() -> clientThread(this, socket, input));
                Log.registerLogger(clientThread, Log.getLoggerOrDefault(getThread()));
                clientThread.start();
            } else if (msg instanceof IwCliSocketClose) {
                sockets.close(((IwCliSocketClose) msg).socket);
            } else if (msg instanceof IwCliServerReceive) {
                sockets.mark(((IwCliServerReceive) msg).socket);
                var clientId = sockets.getId(((IwCliServerReceive) msg).socket);
                if (clientId != null) {
                    cliTask.push(new IwCliClientMessage(clientId, ((IwCliServerReceive) msg).data));
                }
            } else if (msg instanceof IwCliServerMessage) {
                var client = ((IwCliServerMessage) msg).client;
                var data = ((IwCliServerMessage) msg).data;

                sockets.writeToClient(client, data);
            } else if (msg instanceof IwPerformCycle) {
                var type = ((IwPerformCycle) msg).type;
                if (type == CYCLE_TYPE_HEARTBEAT) {
                    sockets.sweep();
                    pushDelayed(new IwPerformCycle(CYCLE_TYPE_HEARTBEAT), HEARTBEAT_PERIOD);
                }
            }
        }
    }
}
