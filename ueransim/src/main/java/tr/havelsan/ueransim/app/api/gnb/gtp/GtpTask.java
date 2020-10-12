package tr.havelsan.ueransim.app.api.gnb.gtp;

import tr.havelsan.ueransim.app.itms.Itms;
import tr.havelsan.ueransim.app.itms.ItmsTask;
import tr.havelsan.ueransim.app.itms.wrappers.UplinkDataWrapper;
import tr.havelsan.ueransim.app.structs.simctx.GnbSimContext;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class GtpTask extends ItmsTask {

    private final GnbSimContext ctx;

    public GtpTask(Itms itms, int taskId, GnbSimContext ctx) {
        super(itms, taskId);
        this.ctx = ctx;
    }

    @Override
    public void main() {
        while (true) {
            var msg = itms.receiveMessage(this);
            if (msg instanceof UplinkDataWrapper) {
                handleUplinkData(((UplinkDataWrapper) msg).ipData);
            }
        }
    }

    private void handleUplinkData(OctetString data) {
    }
}