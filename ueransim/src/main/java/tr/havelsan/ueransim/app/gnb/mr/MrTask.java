/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.gnb.mr;

import tr.havelsan.ueransim.app.common.itms.*;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.app.gnb.ngap.NgapNasTransport;
import tr.havelsan.ueransim.itms.Itms;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.itms.ItmsTask;
import tr.havelsan.ueransim.nas.NasDecoder;

public class MrTask extends ItmsTask {

    private final GnbSimContext ctx;

    public MrTask(Itms itms, int taskId, GnbSimContext ctx) {
        super(itms, taskId);
        this.ctx = ctx;
    }

    @Override
    public void main() {
        while (true) {
            var msg = itms.receiveMessage(this);
            if (msg instanceof IwUplinkNas) {
                var w = (IwUplinkNas) msg;
                NgapNasTransport.receiveUplinkNasTransport(ctx, w.ue, NasDecoder.nasPdu(w.nasPdu));
            } else if (msg instanceof IwDownlinkNas) {
                var w = (IwDownlinkNas) msg;
                ctx.sim.findUe(w.ue).itms.sendMessage(ItmsId.UE_TASK_MR, new IwDownlinkNas(w.ue, w.nasPdu));
            } else if (msg instanceof IwConnectionRelease) {
                var w = (IwConnectionRelease) msg;
                ctx.sim.findUe(w.ue).itms.sendMessage(ItmsId.UE_TASK_MR, new IwConnectionRelease(w.ue));
            } else if (msg instanceof IwUplinkData) {
                itms.sendMessage(ItmsId.GNB_TASK_GTP, msg);
            } else if (msg instanceof IwDownlinkData) {
                var w = (IwDownlinkData) msg;

                ctx.sim.getAirCtx().itms.sendMessage(ItmsId.AIR_TASK_TB, msg);
                ctx.sim.findUe(w.ueId).itms.sendMessage(ItmsId.UE_TASK_MR, msg);
            }
        }
    }
}
