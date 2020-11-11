package tr.havelsan.ueransim.app.ue.app;

import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.app.common.testcmd.TestCmd_Ping;
import tr.havelsan.ueransim.utils.octets.OctetString;

class PingApp {
    public final UeSimContext ctx;

    public PingApp(UeSimContext ctx) {
        this.ctx = ctx;
    }

    public static native byte[] createPingPacket(int src, int dest, short id, short seq);

    public OctetString sendPing(TestCmd_Ping ping) {
        var packet = createPingPacket(0, 0, (short)0, (short)0);

        return new OctetString(packet);
    }
}
