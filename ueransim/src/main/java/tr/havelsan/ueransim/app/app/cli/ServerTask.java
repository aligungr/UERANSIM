/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.app.cli;

import tr.havelsan.ueransim.app.common.nts.*;
import tr.havelsan.ueransim.nts.nts.NtsTask;
import tr.havelsan.ueransim.utils.Constants;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;
import tr.havelsan.ueransim.utils.console.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class ServerTask extends NtsTask {

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
            serverSocket = new ServerSocket(Constants.CLI__PORT);
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
        CliUtils.listenerLoop(input,
                bytes -> task.push(new IwCliServerReceive(socket, bytes)),
                () -> task.push(new IwCliSocketClose(socket))
        );
    }

    @Override
    protected void main() {
        Log.registerLogger(getThread(), Logger.GLOBAL);

        var acceptorThread = new Thread(() -> acceptorThread(this));
        Log.registerLogger(acceptorThread, Log.getLoggerOrDefault(getThread()));
        acceptorThread.start();

        pushDelayed(new IwPerformCycle(Constants.CLI__CYCLE_TYPE_HEARTBEAT), Constants.CLI__HEARTBEAT_PERIOD);

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
                if (type == Constants.CLI__CYCLE_TYPE_HEARTBEAT) {
                    sockets.sweep();
                    pushDelayed(new IwPerformCycle(Constants.CLI__CYCLE_TYPE_HEARTBEAT), Constants.CLI__HEARTBEAT_PERIOD);
                }
            }
        }
    }
}
