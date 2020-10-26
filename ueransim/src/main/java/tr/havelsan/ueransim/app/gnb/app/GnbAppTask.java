package tr.havelsan.ueransim.app.gnb.app;

import tr.havelsan.ueransim.app.common.itms.IwInitialSctpReady;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.itms.Itms;
import tr.havelsan.ueransim.itms.ItmsTask;

public class GnbAppTask extends ItmsTask {

    private final GnbSimContext ctx;
    private boolean initialSctpReady;

    public GnbAppTask(Itms itms, int taskId, GnbSimContext ctx) {
        super(itms, taskId);
        this.ctx = ctx;
    }

    @Override
    public void main() {
        while (true) {
            var msg = ctx.itms.receiveMessage(this);
            if (msg instanceof IwInitialSctpReady) {
                initialSctpReady = true;
            }
        }
    }

    public boolean isInitialSctpReady() {
        return initialSctpReady;
    }
}
