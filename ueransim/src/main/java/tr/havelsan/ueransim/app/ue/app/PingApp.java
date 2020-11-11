package tr.havelsan.ueransim.app.ue.app;

import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.app.common.testcmd.TestCmd_Ping;
import tr.havelsan.ueransim.utils.octets.OctetString;

class PingApp {
    public final UeSimContext ctx;

    public PingApp(UeSimContext ctx) {
        this.ctx = ctx;
    }

    public OctetString sendPing(TestCmd_Ping ping) {
        return new OctetString(createPingPacket());
    }

    public static native byte[] createPingPacket();
}
