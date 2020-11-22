/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.gnb.mr;

import tr.havelsan.ueransim.app.common.itms.*;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.app.gnb.ngap.NgapNasTransport;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.itms.nts.NtsTask;
import tr.havelsan.ueransim.nas.NasDecoder;

public class GnbMrTask extends NtsTask {

    private final GnbSimContext ctx;
    private NtsTask gtpTask;

    public GnbMrTask(GnbSimContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void main() {
        gtpTask = ctx.nts.findTask(ItmsId.GNB_TASK_GTP);

        while (true) {
            var msg = take();
            if (msg instanceof IwUplinkNas) {
                var w = (IwUplinkNas) msg;
                NgapNasTransport.receiveUplinkNasTransport(ctx, w.ue, NasDecoder.nasPdu(w.nasPdu));
            } else if (msg instanceof IwDownlinkNas) {
                var w = (IwDownlinkNas) msg;
                ctx.sim.findUe(w.ue).nts.findTask(ItmsId.UE_TASK_MR).push(new IwDownlinkNas(w.ue, w.nasPdu));
            } else if (msg instanceof IwConnectionRelease) {
                var w = (IwConnectionRelease) msg;
                ctx.sim.findUe(w.ue).nts.findTask(ItmsId.UE_TASK_MR).push(new IwConnectionRelease(w.ue));
            } else if (msg instanceof IwUplinkData) {
                gtpTask.push(msg);
            } else if (msg instanceof IwDownlinkData) {
                var w = (IwDownlinkData) msg;

                ctx.sim.getAirCtx().nts.findTask(ItmsId.AIR_TASK_TB).push(msg);
                ctx.sim.findUe(w.ueId).nts.findTask(ItmsId.UE_TASK_MR).push(msg);
            }
        }
    }
}
