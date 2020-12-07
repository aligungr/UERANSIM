/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.cli;

import tr.havelsan.ueransim.app.common.itms.IwCliClientMessage;
import tr.havelsan.ueransim.app.common.itms.IwCliSocketAccept;
import tr.havelsan.ueransim.itms.nts.NtsTask;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.console.Log;
import tr.havelsan.ueransim.utils.console.Logger;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class CliTask extends NtsTask {

    private static final int RECEIVER_BUFFER_SIZE = 8 * 1024;

    private List<Socket> sockets;

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

            // TODO
            //socket.setSoTimeout();

            task.push(new IwCliSocketAccept(socket));
        }
    }

    private static void clientThread(NtsTask task, Socket socket) {
        InputStream input;
        DataOutputStream output;

        try {
            input = socket.getInputStream();
        } catch (IOException ignored) {
            return;
        }

        try {
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ignored1) {
            try {
                input.close();
            } catch (IOException ignored2) {
                return;
            }
            return;
        }

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

                task.push(new IwCliClientMessage(socket, data));
            }
        }

        Utils.runUnchecked(input::close);
        Utils.runUnchecked(output::close);
        Utils.runUnchecked(socket::close);
    }

    @Override
    public void main() {
        sockets = new ArrayList<>();

        Log.registerLogger(getThread(), Logger.GLOBAL);

        var acceptorThread = new Thread(() -> acceptorThread(this));
        Log.registerLogger(acceptorThread, Log.getLoggerOrDefault(getThread()));
        acceptorThread.start();

        while (true) {
            var msg = take();
            if (msg instanceof IwCliSocketAccept) {
                var socket = ((IwCliSocketAccept) msg).socket;
                sockets.add(socket);

                var clientThread = new Thread(() -> clientThread(this, socket));
                Log.registerLogger(clientThread, Log.getLoggerOrDefault(getThread()));
                clientThread.start();
            } else if (msg instanceof IwCliClientMessage) {
                // TODO
            }
        }
    }
}
