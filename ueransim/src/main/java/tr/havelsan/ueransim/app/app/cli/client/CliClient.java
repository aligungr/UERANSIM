/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.cli.client;

import tr.havelsan.ueransim.app.app.cli.CliUtils;
import tr.havelsan.ueransim.app.common.cli.CmdMessage;
import tr.havelsan.ueransim.itms.nts.NtsTask;
import tr.havelsan.ueransim.utils.Constants;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class CliClient {

    private static final Object SOCKET_CLOSE = new Object();

    private final MainTask mainTask;
    private final SenderTask senderTask;
    private final ListenerTask listenerTask;
    private final CliTerminal cliTerminal;

    private final InputStream inputStream;
    private final OutputStream outputStream;

    public CliClient() throws Exception {
        var socket = new Socket("127.0.0.1", Constants.CLI__PORT);

        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();

        mainTask = new MainTask();
        senderTask = new SenderTask();
        listenerTask = new ListenerTask();

        cliTerminal = new CliTerminal(mainTask::push);

        mainTask.start();
        senderTask.start();
        listenerTask.start();

        cliTerminal.main();
    }

    private static void fatalError(String message) {
        System.err.println(message);
        System.exit(1);
    }

    private class MainTask extends NtsTask {

        @Override
        public void main() {
            while (true) {
                var msg = take();
                if (msg instanceof byte[]) {
                    var cmd = CliUtils.decodeCmdPdu((byte[]) msg, CliClient::fatalError);
                    cliTerminal.onReceive(cmd);
                } else if (msg == SOCKET_CLOSE) {
                    cliTerminal.onSocketClose();
                } else if (msg instanceof CmdMessage) {
                    senderTask.push(CliUtils.encodeCmdPdu((CmdMessage) msg));
                }
            }
        }
    }

    private class SenderTask extends NtsTask {

        @Override
        public void main() {
            while (true) {
                var msg = take();
                if (msg instanceof byte[]) {
                    var data = (byte[]) msg;
                    try {
                        outputStream.write(data);
                        outputStream.flush();
                    } catch (Exception e) {
                        fatalError("ERROR: Command socket could not write.");
                    }
                }
            }
        }
    }

    private class ListenerTask extends NtsTask {

        @Override
        public void main() {
            CliUtils.listenerLoop(inputStream, mainTask::push, () -> mainTask.push(SOCKET_CLOSE));
        }
    }
}
