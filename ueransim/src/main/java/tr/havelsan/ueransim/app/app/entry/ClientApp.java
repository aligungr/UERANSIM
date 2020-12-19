/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.entry;

import picocli.CommandLine;
import tr.havelsan.ueransim.app.app.cli.CliOpt;
import tr.havelsan.ueransim.app.app.cli.CliUtils;
import tr.havelsan.ueransim.app.common.cli.*;
import tr.havelsan.ueransim.app.common.nts.IwPerformCycle;
import tr.havelsan.ueransim.nts.nts.NtsTask;
import tr.havelsan.ueransim.utils.Constants;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.console.Console;
import tr.havelsan.ueransim.utils.jcolor.AnsiPalette;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;

public class ClientApp {

    private static final Object SOCKET_CLOSE = new Object();

    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    private MainTask mainTask;
    private SenderTask senderTask;
    private ListenerTask listenerTask;

    private boolean exiting;

    public ClientApp() {
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

    public static void main(String[] args) {
        BaseApp.main(args, false);
        new ClientApp().start(args);
    }

    protected void start(String[] args) {
        CliOpt.msg = null;
        new CommandLine(new CliOpt.RootCommand())
                .registerConverter(OctetString.class, OctetString::new)
                .execute(args);

        if (CliOpt.msg != null) {
            send(CliOpt.msg);
        } else {
            exit(1);
        }
    }

    protected void onReceive(CmdMessage cmd) {
        if (cmd instanceof CmdErrorIndication) {
            fatalError("ERROR: " + ((CmdErrorIndication) cmd).message);
        } else if (cmd instanceof CmdEcho) {
            System.out.println(((CmdEcho) cmd).message);
        } else if (cmd instanceof CmdPrint) {
            System.out.println(((CmdPrint) cmd).message);
        } else if (cmd instanceof CmdTerminate) {
            if (((CmdTerminate) cmd).finalOutput != null)
                System.out.println(((CmdTerminate) cmd).finalOutput);
            exit(((CmdTerminate) cmd).code);
        }
    }

    protected final void send(CmdMessage cmd) {
        mainTask.push(cmd);
    }

    protected void fatalError(String message) {
        if (exiting) {
            System.exit(0);
            return;
        }

        Console.println(AnsiPalette.PAINT_LOG_ERROR, message);
        System.exit(1);
    }

    protected void exit(int code) {
        exiting = true;

        Utils.runUnchecked(() -> inputStream.close());
        Utils.runUnchecked(() -> outputStream.close());
        Utils.runUnchecked(() -> socket.close());

        System.exit(code);
    }

    public static class VersionProvider implements CommandLine.IVersionProvider {

        public String[] getVersion() {
            return new String[]{Constants.VERSION};
        }
    }

    private class MainTask extends NtsTask {

        public MainTask() {
            super(true);
        }

        @Override
        protected void main() {
            final int heartbeat = Constants.CLI__HEARTBEAT_PERIOD / 2;

            pushDelayed(new IwPerformCycle(Constants.CLI__CYCLE_TYPE_HEARTBEAT), heartbeat);

            while (true) {
                var msg = take();
                if (msg instanceof byte[]) {
                    var cmd = CliUtils.decodeCmdPdu((byte[]) msg, ClientApp.this::fatalError);
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
        protected void main() {
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
        protected void main() {
            CliUtils.listenerLoop(inputStream, mainTask::push, () -> mainTask.push(SOCKET_CLOSE));
        }
    }
}
