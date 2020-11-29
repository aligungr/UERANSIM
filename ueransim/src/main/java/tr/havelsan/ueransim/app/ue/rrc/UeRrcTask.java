/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue.rrc;

import tr.havelsan.ueransim.app.common.contexts.UeRrcContext;
import tr.havelsan.ueransim.app.common.itms.IwDownlinkRrc;
import tr.havelsan.ueransim.app.common.itms.IwPlmnSearchResponse;
import tr.havelsan.ueransim.app.common.itms.IwUplinkNas;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.itms.nts.NtsTask;

public class UeRrcTask extends NtsTask {

    private final UeRrcContext ctx;

    public UeRrcTask(UeSimContext ueCtx) {
        this.ctx = new UeRrcContext(ueCtx);
    }

    @Override
    public void main() {
        ctx.mrTask = ctx.ueCtx.nts.findTask(ItmsId.UE_TASK_MR);
        ctx.nasTask = ctx.ueCtx.nts.findTask(ItmsId.UE_TASK_NAS);

        while (true) {
            var msg = take();
            if (msg instanceof IwDownlinkRrc) {
                RrcTransport.receiveRrcMessage(ctx, ((IwDownlinkRrc) msg).rrcMessage);
            } else if (msg instanceof IwUplinkNas) {
                RrcTransport.deliverUplinkNas(ctx, ((IwUplinkNas) msg).nasPdu);
            } else if (msg instanceof IwPlmnSearchResponse) {
                ctx.nasTask.push(msg);
            }
        }
    }
}
