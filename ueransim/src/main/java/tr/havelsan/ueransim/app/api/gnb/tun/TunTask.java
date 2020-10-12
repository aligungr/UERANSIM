package tr.havelsan.ueransim.app.api.gnb.tun;

import tr.havelsan.ueransim.app.itms.Itms;
import tr.havelsan.ueransim.app.itms.ItmsTask;
import tr.havelsan.ueransim.app.structs.simctx.GnbSimContext;

public class TunTask extends ItmsTask {

    private final GnbSimContext ctx;

    public TunTask(Itms itms, int taskId, GnbSimContext ctx) {
        super(itms, taskId);
        this.ctx = ctx;
    }

    @Override
    public void main() {

    }
}