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

class PingApp {
    private final UeSimContext ctx;
    private final UeConnectionInfo connectionInfo;

    public PingApp(UeSimContext ctx, UeConnectionInfo connectionInfo) {
        this.ctx = ctx;
        this.connectionInfo = connectionInfo;
    }

    public static native byte[] createPingPacket(int src, int dest, short id, short seq);

    public void sendPing(TestCmd_Ping ping) {
        var packet = createPingPacket(0, 0, (short) 0, (short) 0);

        if (!connectionInfo.isEstablished) {
            Log.error(Tag.UE_APP, "Ping failure: UE has no connection.");
            return;
        }

        if (!connectionInfo.sessionType.equals(EPduSessionType.IPV4)) {
            Log.error(Tag.UE_APP, "Cannot ping for current PDU Session Type.");
            return;
        }

        ctx.itms.sendMessage(ItmsId.UE_TASK_MR, new IwUplinkData(ctx.ctxId, connectionInfo.pduSessionId, new OctetString(packet)));
    }
}
