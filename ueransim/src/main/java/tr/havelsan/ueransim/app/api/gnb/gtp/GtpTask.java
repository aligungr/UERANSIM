package tr.havelsan.ueransim.app.api.gnb.gtp;

import tr.havelsan.ueransim.app.itms.Itms;
import tr.havelsan.ueransim.app.itms.ItmsTask;
import tr.havelsan.ueransim.app.itms.wrappers.PduSessionResourceCreateWrapper;
import tr.havelsan.ueransim.app.itms.wrappers.UplinkDataWrapper;
import tr.havelsan.ueransim.app.structs.GtpUContext;
import tr.havelsan.ueransim.app.structs.simctx.GnbSimContext;

public class GtpTask extends ItmsTask {

    private final GnbSimContext ctx;
    private GtpUContext gtpCtx;

    public GtpTask(Itms itms, int taskId, GnbSimContext ctx) {
        super(itms, taskId);
        this.ctx = ctx;
    }

    @Override
    public void main() {
        this.gtpCtx = ctx.gtpUCtx = new GtpUContext();

        while (true) {
            var msg = itms.receiveMessage(this);
            if (msg instanceof UplinkDataWrapper) {
                var data = ((UplinkDataWrapper) msg).ipData;
                if ((data.get(0) >> 4 & 0xF) != 4) {
                    // ignore non IPv4 packets
                    return;
                }

                // TODO
            } else if (msg instanceof PduSessionResourceCreateWrapper) {
                // TODO
            }
        }
    }
}