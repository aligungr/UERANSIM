/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.gnb.mr;

import tr.havelsan.ueransim.app.common.contexts.GnbMrContext;
import tr.havelsan.ueransim.app.common.itms.IwDownlinkData;
import tr.havelsan.ueransim.app.common.itms.IwDownlinkRrc;
import tr.havelsan.ueransim.app.common.itms.IwUplinkData;
import tr.havelsan.ueransim.app.common.itms.IwUplinkRrc;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.itms.nts.NtsTask;

public class GnbMrTask extends NtsTask {

    private final GnbMrContext ctx;

    public GnbMrTask(GnbSimContext ctx) {
        this.ctx = new GnbMrContext(ctx);
    }

    @Override
    public void main() {
        ctx.gtpTask = ctx.gnbCtx.nts.findTask(ItmsId.GNB_TASK_GTP);
        ctx.rrcTask = ctx.gnbCtx.nts.findTask(ItmsId.GNB_TASK_RRC);

        while (true) {
            var msg = take();
            if (msg instanceof IwUplinkRrc) {
                receiveUplinkRrc((IwUplinkRrc) msg);
            } else if (msg instanceof IwDownlinkRrc) {
                receiveDownlinkRrc((IwDownlinkRrc) msg);
            } else if (msg instanceof IwUplinkData) {
                receiveUplinkData((IwUplinkData) msg);
            } else if (msg instanceof IwDownlinkData) {
                receiveDownlinkData((IwDownlinkData) msg);
            }
        }
    }

    private void receiveUplinkRrc(IwUplinkRrc msg) {
        ctx.rrcTask.push(msg);
    }

    private void receiveDownlinkRrc(IwDownlinkRrc msg) {
        ctx.gnbCtx.sim.findUe(msg.ueId).nts.findTask(ItmsId.UE_TASK_MR).push(msg);
    }

    private void receiveUplinkData(IwUplinkData msg) {
        ctx.gtpTask.push(msg);
    }

    private void receiveDownlinkData(IwDownlinkData msg) {
        ctx.gnbCtx.sim.getAirCtx().nts.findTask(ItmsId.AIR_TASK_TB).push(msg);
        ctx.gnbCtx.sim.findUe(msg.ueId).nts.findTask(ItmsId.UE_TASK_MR).push(msg);
    }
}
