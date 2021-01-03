/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.ue.app;

import tr.havelsan.ueransim.app.common.nts.IwDownlinkData;
import tr.havelsan.ueransim.app.common.nts.IwUplinkData;
import tr.havelsan.ueransim.app.utils.Native;
import tr.havelsan.ueransim.nts.nts.NtsTask;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.nio.ByteBuffer;
import java.util.UUID;
import java.util.function.Consumer;

class TunTask extends NtsTask {

    private final UeAppTask appTask;
    private final UUID ueId;
    private final int psi;
    private final int fd;

    public TunTask(UeAppTask appTask, UUID ueId, int psi, int fd) {
        this.appTask = appTask;
        this.ueId = ueId;
        this.psi = psi;
        this.fd = fd;
    }

    private static void tunReaderThread(int fd, Consumer<OctetString> consumer) {
        var buffer = ByteBuffer.allocateDirect(65535); // TODO set to mtu 1500?

        while (true) {
            int read = Native.read(fd, buffer);
            if (read < 0) {
                Log.error(Tag.TUN, "TUN device could not read.");
                return; // abort thread
            }

            // TODO: optimize this
            var bytes = new byte[read];
            for (int i = 0; i < read; i++) {
                bytes[i] = buffer.get(i);
            }

            consumer.accept(new OctetString(bytes));
        }
    }

    @Override
    protected void main() {
        var tunReaderThread = new Thread(()
                -> tunReaderThread(fd, data -> appTask.push(new IwUplinkData(ueId, psi, data))));
        Log.registerLogger(tunReaderThread, Log.getLoggerOrDefault(getThread()));
        tunReaderThread.start();

        var buffer = ByteBuffer.allocateDirect(65535); // TODO set to mtu 1500?

        while (true) {
            var msg = take();
            if (msg instanceof IwDownlinkData) {

                // TODO: optimize this
                var data = ((IwDownlinkData) msg).ipPacket;
                for (int i = 0; i < data.length; i++) {
                    buffer.put(i, data.get(i));
                }

                int res = Native.write(fd, buffer, data.length);

                if (res < 0) {
                    Log.error(Tag.TUN, "TUN device could not write.");
                    return; // abort thread
                }

                if (res != data.length) {
                    Log.warning(Tag.TUN, "TUN device partially written.");
                }
            }
        }
    }
}
