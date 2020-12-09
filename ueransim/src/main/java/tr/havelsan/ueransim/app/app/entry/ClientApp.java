/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.entry;

import tr.havelsan.ueransim.app.app.cli.CommandLine;
import tr.havelsan.ueransim.app.common.cli.CmdMessage;
import tr.havelsan.ueransim.itms.nts.NtsTask;
import tr.havelsan.ueransim.utils.Constants;
import tr.havelsan.ueransim.utils.Json;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientApp {

    private static final Object SOCKET_CLOSE = new Object();

    private static MainTask mainTask;
    private static SenderTask senderTask;
    private static ListenerTask listenerTask;
    private static CommandLine commandLine;

    public static void main(String[] args) throws Exception {
        BaseApp.main(args);

        var socket = new Socket("127.0.0.1", Constants.CLI__PORT);

        mainTask = new MainTask();
        senderTask = new SenderTask(socket.getOutputStream());
        listenerTask = new ListenerTask(socket.getInputStream());

        commandLine = new CommandLine(cmdMessage -> mainTask.push(cmdMessage));

        mainTask.start();
        senderTask.start();
        listenerTask.start();

        commandLine.main();
    }

    private static class MainTask extends NtsTask {

        private static byte[] encodeCmdPdu(CmdMessage cmd) {
            var stream = new OctetOutputStream();

            stream.writeOctet2(0); // length

            stream.writeOctet(Constants.MAJOR);
            stream.writeOctet(Constants.MINOR);
            stream.writeOctet(Constants.PATCH);

            var typeName = cmd.getClass().getName().getBytes(StandardCharsets.UTF_8);
            stream.writeOctet2(typeName.length);
            stream.writeOctets(typeName);

            var json = Json.toJson(cmd).getBytes(StandardCharsets.UTF_8);
            stream.writeOctet2(json.length);
            stream.writeOctets(json);

            var bytes = stream.toByteArray();

            var length = bytes.length - 2;

            bytes[0] = (byte) (length >> 8 & 0xFF);
            bytes[1] = (byte) (length & 0xFF);
            return bytes;
        }

        @Override
        public void main() {
            while (true) {
                var msg = take();
                if (msg instanceof Exception) {
                    commandLine.onException((Exception) msg);
                } else if (msg instanceof byte[]) {
                    var stream = new OctetInputStream((byte[]) msg);
                    int major = stream.readOctetI();
                    int minor = stream.readOctetI();
                    int patch = stream.readOctetI();
                    if (major != Constants.MAJOR || minor != Constants.MINOR || patch != Constants.PATCH) {
                        System.err.println("ERROR: Version mismatch between UERANSIM instances.");
                        System.exit(1);
                        continue;
                    }

                    CmdMessage decoded;

                    int length = stream.readOctet2I();
                    try {
                        var clsName = new String(stream.readOctetString(length).toByteArray(), StandardCharsets.UTF_8);
                        var cls = Class.forName(clsName);

                        length = stream.readOctet2I();
                        var json = new String(stream.readOctetString(length).toByteArray(), StandardCharsets.UTF_8);

                        decoded = Json.fromJson(json, cls);

                    } catch (Exception e) {
                        commandLine.onException(e);
                        continue;
                    }

                    commandLine.onReceive(decoded);

                } else if (msg == SOCKET_CLOSE) {
                    commandLine.onSocketClose();
                } else if (msg instanceof CmdMessage) {
                    var data = encodeCmdPdu((CmdMessage) msg);
                    senderTask.push(data);
                }
            }
        }
    }

    private static class SenderTask extends NtsTask {

        private final OutputStream stream;

        public SenderTask(OutputStream stream) {
            this.stream = stream;
        }

        @Override
        public void main() {
            while (true) {
                var msg = take();
                if (msg instanceof byte[]) {
                    var data = (byte[]) msg;
                    try {
                        stream.write(data);
                        stream.flush();
                    } catch (Exception e) {
                        mainTask.push(e);
                    }
                }
            }
        }
    }

    private static class ListenerTask extends NtsTask {
        private final InputStream stream;

        public ListenerTask(InputStream stream) {
            this.stream = stream;
        }

        @Override
        public void main() {
            final int STATE_FIRST_LENGTH = 0;
            final int STATE_SECOND_LENGTH = 1;
            final int STATE_DATA = 2;

            var buffer = new byte[Constants.CLI__RECEIVER_BUFFER_SIZE];
            int offset = 0, length = 0;
            int state = STATE_FIRST_LENGTH;

            outer:
            while (true) {
                int read;
                try {
                    read = stream.read();
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
                    if (length > Constants.CLI__RECEIVER_BUFFER_SIZE) {
                        break;
                    }

                    buffer[offset++] = (byte) read;
                    for (int i = 0; i < length - 1; i++) {
                        int r;
                        try {
                            r = stream.read();
                        } catch (IOException e) {
                            break outer;
                        }
                        if (r < 0)
                            break outer;
                        buffer[offset++] = (byte) r;
                    }

                    offset = 0;
                    state = STATE_FIRST_LENGTH;

                    var data = new byte[length];
                    System.arraycopy(buffer, 0, data, 0, length);

                    mainTask.push(data);
                }
            }

            mainTask.push(SOCKET_CLOSE);
        }
    }
}
