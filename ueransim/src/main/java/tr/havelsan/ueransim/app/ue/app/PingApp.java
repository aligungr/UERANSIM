/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue.app;

import tr.havelsan.ueransim.app.common.cli.CmdUePing;
import tr.havelsan.ueransim.app.common.info.UeConnectionInfo;
import tr.havelsan.ueransim.app.common.nts.IwUplinkData;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.nas.impl.enums.EPduSessionType;
import tr.havelsan.ueransim.nts.NtsId;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.console.Log;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

class PingApp {
    private final UeSimContext ctx;
    private final UeConnectionInfo connectionInfo;
    private final Map<Integer, PingEntry> pingEntries;

    private short idCounter;
    private short seqCounter;
    private long lastTimeoutControl;

    public PingApp(UeSimContext ctx, UeConnectionInfo connectionInfo) {
        this.ctx = ctx;
        this.connectionInfo = connectionInfo;
        this.pingEntries = new HashMap<>();
    }

    private static native byte[] createPingPacket(int src, int dest, short id, short seq);

    private static native int handleEchoReplyPacket(byte[] ipData);

    public void sendPing(CmdUePing ping) {
        if (!connectionInfo.isEstablished) {
            Log.error(Tag.UEAPP, "Ping failure: UE has no connection.");
            return;
        }
        if (!connectionInfo.sessionType.equals(EPduSessionType.IPV4)) {
            Log.error(Tag.UEAPP, "Ping failure: PDU Session Type is not supported.");
            return;
        }

        int source = ((connectionInfo.pduAddress[0] & 0xFF) << 24)
                | ((connectionInfo.pduAddress[1] & 0xFF) << 16)
                | ((connectionInfo.pduAddress[2] & 0xFF) << 8)
                | (connectionInfo.pduAddress[3] & 0xFF);

        int dest;
        String destAddrName;

        try {
            var destAddr = InetAddress.getByName(ping.address).getAddress();
            dest = ((destAddr[0] & 0xFF) << 24) | ((destAddr[1] & 0xFF) << 16)
                    | ((destAddr[2] & 0xFF) << 8) | (destAddr[3] & 0xFF);
            destAddrName = Utils.byteArrayToIpString(destAddr);
        } catch (Exception e) {
            Log.error(Tag.UEAPP, "Ping failure: Name resolution failed for name '%s'.", ping.address);
            return;
        }

        for (int i = 0; i < ping.count; i++) {
            short id = ++idCounter;
            short seq = ++seqCounter;

            if (id == 0) id++;
            if (seq == 0) seq++;

            pingEntries.put(id << 16 | seq, new PingEntry(System.currentTimeMillis(), ping.address, destAddrName, ping.timeoutSec));

            var packet = createPingPacket(source, dest, id, seq);

            ctx.nts.findTask(NtsId.UE_TASK_MR).push(new IwUplinkData(ctx.ctxId, connectionInfo.pduSessionId, new OctetString(packet)));
        }
    }

    public void handlePacket(OctetString ipPacket) {
        if ((ipPacket.get(0) >> 4) != 4) return; // Drop if not IPv4
        if (ipPacket.get(9) != 1) return; // Drop if not ICMP

        int r = handleEchoReplyPacket(ipPacket.toByteArray());
        if (r == 0) {
            return;
        }

        var entry = pingEntries.get(r);
        if (entry == null) {
            return;
        }

        pingEntries.remove(r);

        long delta = System.currentTimeMillis() - entry.timestamp;

        Log.success(Tag.UEAPP, "Ping reply from %s in %d ms", entry.getAddressDisplay(), delta);
    }

    public void timeoutControl() {
        long current = System.currentTimeMillis();
        {
            long delta = current - lastTimeoutControl;
            if (delta <= 1000)
                return;
        }

        lastTimeoutControl = current;

        var entriesToDelete = new HashSet<Integer>();

        for (var item : pingEntries.entrySet()) {
            var value = item.getValue();

            if (value.timeoutSec <= 0)
                continue;

            var delta = current - value.timestamp;

            if (delta > value.timeoutSec * 1000) {
                entriesToDelete.add(item.getKey());
            }
        }

        for (var key : entriesToDelete) {
            var value = pingEntries.get(key);
            pingEntries.remove(key);

            Log.error(Tag.UEAPP, "Ping timeout for %s after %d sec no response", value.getAddressDisplay(), value.timeoutSec);
        }
    }
}
