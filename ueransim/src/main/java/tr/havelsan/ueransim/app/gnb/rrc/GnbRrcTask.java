/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.gnb.rrc;

import tr.havelsan.ueransim.app.common.contexts.GnbRrcContext;
import tr.havelsan.ueransim.app.common.itms.IwDownlinkNas;
import tr.havelsan.ueransim.app.common.itms.IwUplinkRrc;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.itms.NtsId;
import tr.havelsan.ueransim.itms.nts.NtsTask;

public class GnbRrcTask extends NtsTask {

    private final GnbRrcContext ctx;

    public GnbRrcTask(GnbSimContext ctx) {
        this.ctx = new GnbRrcContext(ctx);
    }

    @Override
    public void main() {
        ctx.ngapTask = ctx.gnbCtx.nts.findTask(NtsId.GNB_TASK_NGAP);
        ctx.mrTask = ctx.gnbCtx.nts.findTask(NtsId.GNB_TASK_MR);

        while (true) {
            var msg = take();
            if (msg instanceof IwUplinkRrc) {
                RrcTransport.receiveRrcMessage(ctx, ((IwUplinkRrc) msg).ueId, ((IwUplinkRrc) msg).rrcMessage);
            } else if (msg instanceof IwDownlinkNas) {
                RrcNasTransport.deliverDlNas(ctx, ((IwDownlinkNas) msg).ue, ((IwDownlinkNas) msg).nasPdu);
            }
        }
    }
}
