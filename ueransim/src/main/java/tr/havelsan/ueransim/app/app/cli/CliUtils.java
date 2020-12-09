/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.cli;

import tr.havelsan.ueransim.app.common.cli.CmdMessage;
import tr.havelsan.ueransim.utils.Constants;
import tr.havelsan.ueransim.utils.Json;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.function.Consumer;

public class CliUtils {

    public static void listenerLoop(InputStream stream, Consumer<byte[]> onReceive, Runnable onFinish) {
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

                onReceive.accept(data);
            }
        }

        onFinish.run();
    }

    public static byte[] encodeCmdPdu(CmdMessage cmd) {
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

    public static CmdMessage decodeCmdPdu(byte[] data, Consumer<String> onError) {
        var stream = new OctetInputStream(data);

        int major = stream.readOctetI();
        int minor = stream.readOctetI();
        int patch = stream.readOctetI();

        if (major != Constants.MAJOR || minor != Constants.MINOR || patch != Constants.PATCH) {
            onError.accept("ERROR: Version mismatch between UERANSIM instances.");
            return null;
        }

        CmdMessage decoded;

        int length = stream.readOctet2I();
        try {
            var clsName = new String(stream.readOctetString(length).toByteArray(), StandardCharsets.UTF_8);
            var cls = Class.forName(clsName);

            if (!CmdMessage.class.isAssignableFrom(cls)) {
                onError.accept("ERROR: Bad CLI message class.");
                return null;
            }

            length = stream.readOctet2I();
            var json = new String(stream.readOctetString(length).toByteArray(), StandardCharsets.UTF_8);

            decoded = Json.fromJson(json, cls);

        } catch (Exception e) {
            onError.accept("ERROR: CLI command decoding failed.");
            return null;
        }

        return decoded;
    }

    public static String[] translateCommandline(String toProcess) {
        if (toProcess == null || toProcess.isEmpty()) {
            //no command? no string
            return new String[0];
        }
        // parse with a simple finite state machine

        final int normal = 0;
        final int inQuote = 1;
        final int inDoubleQuote = 2;
        int state = normal;
        final StringTokenizer tok = new StringTokenizer(toProcess, "\"' ", true);
        final ArrayList<String> result = new ArrayList<>();
        final StringBuilder current = new StringBuilder();
        boolean lastTokenHasBeenQuoted = false;

        while (tok.hasMoreTokens()) {
            String nextTok = tok.nextToken();
            switch (state) {
                case inQuote:
                    if ("'".equals(nextTok)) {
                        lastTokenHasBeenQuoted = true;
                        state = normal;
                    } else {
                        current.append(nextTok);
                    }
                    break;
                case inDoubleQuote:
                    if ("\"".equals(nextTok)) {
                        lastTokenHasBeenQuoted = true;
                        state = normal;
                    } else {
                        current.append(nextTok);
                    }
                    break;
                default:
                    if ("'".equals(nextTok)) {
                        state = inQuote;
                    } else if ("\"".equals(nextTok)) {
                        state = inDoubleQuote;
                    } else if (" ".equals(nextTok)) {
                        if (lastTokenHasBeenQuoted || current.length() > 0) {
                            result.add(current.toString());
                            current.setLength(0);
                        }
                    } else {
                        current.append(nextTok);
                    }
                    lastTokenHasBeenQuoted = false;
                    break;
            }
        }
        if (lastTokenHasBeenQuoted || current.length() > 0) {
            result.add(current.toString());
        }
        if (state == inQuote || state == inDoubleQuote) {
            throw new RuntimeException("unbalanced quotes in " + toProcess);
        }
        return result.toArray(new String[0]);
    }
}
