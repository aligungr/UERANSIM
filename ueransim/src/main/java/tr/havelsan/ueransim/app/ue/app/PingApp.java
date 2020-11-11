package tr.havelsan.ueransim.app.ue.app;

import tr.havelsan.ueransim.app.common.UeConnectionInfo;
import tr.havelsan.ueransim.app.common.itms.IwUplinkData;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.app.common.testcmd.TestCmd_Ping;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.nas.impl.enums.EPduSessionType;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

class PingApp {
    private final UeSimContext ctx;
    private final UeConnectionInfo connectionInfo;
    private final Map<Integer, Long> pingEntries; // TODO: timeout

    private short idCounter;
    private short seqCounter;

    public PingApp(UeSimContext ctx, UeConnectionInfo connectionInfo) {
        this.ctx = ctx;
        this.connectionInfo = connectionInfo;
        this.pingEntries = new HashMap<>();
    }

    public static native byte[] createPingPacket(int src, int dest, short id, short seq);

    public void sendPing(TestCmd_Ping ping) {
        if (!connectionInfo.isEstablished) {
            Log.error(Tag.UE_APP, "Ping failure: UE has no connection.");
            return;
        }
        if (!connectionInfo.sessionType.equals(EPduSessionType.IPV4)) {
            Log.error(Tag.UE_APP, "Ping failure: PDU Session Type is not supported.");
            return;
        }

        int source = ((connectionInfo.pduAddress[0] & 0xFF) << 24)
                | ((connectionInfo.pduAddress[1] & 0xFF) << 16)
                | ((connectionInfo.pduAddress[2] & 0xFF) << 8)
                | (connectionInfo.pduAddress[3] & 0xFF);

        int dest;

        try {
            var destAddr = InetAddress.getByName(ping.address).getAddress();
            dest = ((destAddr[0] & 0xFF) << 24) | ((destAddr[1] & 0xFF) << 16)
                    | ((destAddr[2] & 0xFF) << 8) | (destAddr[3] & 0xFF);
        } catch (Exception e) {
            Log.error(Tag.UE_APP, "Ping failure: Name resolution failed for name '%s'.", ping.address);
            return;
        }

        short id = ++idCounter;
        short seq = ++seqCounter;

        pingEntries.put(id << 16 | seq, System.currentTimeMillis());

        var packet = createPingPacket(source, dest, id, seq);
        ctx.itms.sendMessage(ItmsId.UE_TASK_MR, new IwUplinkData(ctx.ctxId, connectionInfo.pduSessionId, new OctetString(packet)));
    }

    public void handlePacket(OctetString ipPacket) {
        // TODO
    }
}
