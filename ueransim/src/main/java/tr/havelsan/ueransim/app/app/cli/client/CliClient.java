/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.cli.client;

import tr.havelsan.ueransim.app.app.cli.CliUtils;
import tr.havelsan.ueransim.app.common.cli.CmdHeartBeat;
import tr.havelsan.ueransim.app.common.cli.CmdMessage;
import tr.havelsan.ueransim.app.common.itms.IwPerformCycle;
import tr.havelsan.ueransim.itms.nts.NtsTask;
import tr.havelsan.ueransim.utils.Constants;
import tr.havelsan.ueransim.utils.Utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;

abstract class CliClient {

    private static final Object SOCKET_CLOSE = new Object();

    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    private MainTask mainTask;
    private SenderTask senderTask;
    private ListenerTask listenerTask;

    private boolean exiting;

    public CliClient() {
        try {
            socket = new Socket("127.0.0.1", Constants.CLI__PORT);
        } catch (ConnectException e) {
            fatalError("ERROR: UERANSIM agent is not running.");
            return;
        } catch (Exception e) {
            fatalError("ERROR: CLI socket could not initialized.");
        }

        try {
            inputStream = socket.getInputStream();
        } catch (Exception e) {
            Utils.runUnchecked(() -> socket.close());

            fatalError("ERROR: Input socket could not open.");
            return;
        }

        try {
            outputStream = socket.getOutputStream();
        } catch (Exception e) {
            Utils.runUnchecked(() -> socket.close());
            Utils.runUnchecked(() -> inputStream.close());

            fatalError("ERROR: Input socket could not open.");
            return;
        }

        mainTask = new MainTask();
        senderTask = new SenderTask();
        listenerTask = new ListenerTask();

        mainTask.start();
        senderTask.start();
        listenerTask.start();
    }

    public final void start(String[] args) {
        main(args);
    }

    protected abstract void main(String[] args);

    protected abstract void onReceive(CmdMessage cmd);

    protected final void send(CmdMessage cmd) {
        mainTask.push(cmd);
    }

    protected void fatalError(String message) {
        if (exiting) {
            System.exit(0);
            return;
        }

        System.err.println(message);
        System.exit(1);
    }

    protected void exit() {
        exiting = true;

        Utils.runUnchecked(() -> inputStream.close());
        Utils.runUnchecked(() -> outputStream.close());
        Utils.runUnchecked(() -> socket.close());

        System.exit(0);
    }

    private class MainTask extends NtsTask {

        public MainTask() {
            super(true);
        }

        @Override
        public void main() {
            final int heartbeat = Constants.CLI__HEARTBEAT_PERIOD / 2;

            pushDelayed(new IwPerformCycle(Constants.CLI__CYCLE_TYPE_HEARTBEAT), heartbeat);

            while (true) {
                var msg = take();
                if (msg instanceof byte[]) {
                    var cmd = CliUtils.decodeCmdPdu((byte[]) msg, CliClient.this::fatalError);
                    onReceive(cmd);
                } else if (msg == SOCKET_CLOSE) {
                    if (!exiting) {
                        fatalError("ERROR: CLI socket closed by agent.");
                    }
                } else if (msg instanceof CmdMessage) {
                    senderTask.push(CliUtils.encodeCmdPdu((CmdMessage) msg));
                } else if (msg instanceof IwPerformCycle) {
                    if (((IwPerformCycle) msg).type == Constants.CLI__CYCLE_TYPE_HEARTBEAT) {
                        send(new CmdHeartBeat());
                        pushDelayed(msg, heartbeat);
                    }
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
