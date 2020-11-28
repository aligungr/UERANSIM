/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.gnb.mr;

import tr.havelsan.ueransim.app.common.itms.IwDownlinkData;
import tr.havelsan.ueransim.app.common.itms.IwDownlinkRrc;
import tr.havelsan.ueransim.app.common.itms.IwUplinkData;
import tr.havelsan.ueransim.app.common.itms.IwUplinkRrc;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.itms.nts.NtsTask;

public class GnbMrTask extends NtsTask {

    private final GnbSimContext ctx;

    public GnbMrTask(GnbSimContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void main() {
        var gtpTask = ctx.nts.findTask(ItmsId.GNB_TASK_GTP);
        var rrcTask = ctx.nts.findTask(ItmsId.GNB_TASK_RRC);

        while (true) {
            var msg = take();
            if (msg instanceof IwUplinkRrc) {
                rrcTask.push(msg);
            } else if (msg instanceof IwDownlinkRrc) {
                ctx.sim.findUe(((IwDownlinkRrc) msg).ueId).nts.findTask(ItmsId.UE_TASK_MR).push(msg);
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
