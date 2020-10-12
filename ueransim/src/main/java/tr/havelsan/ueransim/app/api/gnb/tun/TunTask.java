package tr.havelsan.ueransim.app.api.gnb.tun;

import tr.havelsan.ueransim.app.itms.Itms;
import tr.havelsan.ueransim.app.itms.ItmsTask;
import tr.havelsan.ueransim.app.structs.simctx.GnbSimContext;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;

import java.net.DatagramSocket;

public class TunTask extends ItmsTask {

    private final GnbSimContext ctx;

    public TunTask(Itms itms, int taskId, GnbSimContext ctx) {
        super(itms, taskId);
        this.ctx = ctx;
    }

    @Override
    public void main() {
        DatagramSocket bridge;

        try {
            bridge = new DatagramSocket(ctx.config.tunPort);
        } catch (Exception e) {
            Log.error(Tag.CONNECTION, "TUN Bridge connection could not established: " + e.getMessage());
            return;
        }

        Log.info(Tag.CONNECTION, "Listening TUN Bridge.");
    }
}