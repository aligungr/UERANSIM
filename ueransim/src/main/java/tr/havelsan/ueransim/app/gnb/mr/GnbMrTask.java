/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.gnb.mr;

import tr.havelsan.ueransim.app.common.contexts.GnbMrContext;
import tr.havelsan.ueransim.app.common.nts.IwDownlinkData;
import tr.havelsan.ueransim.app.common.nts.IwDownlinkRrc;
import tr.havelsan.ueransim.app.common.nts.IwUplinkData;
import tr.havelsan.ueransim.app.common.nts.IwUplinkRrc;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.nts.NtsId;
import tr.havelsan.ueransim.nts.nts.NtsTask;

public class GnbMrTask extends NtsTask {

    private final GnbMrContext ctx;

    public GnbMrTask(GnbSimContext ctx) {
        this.ctx = new GnbMrContext(ctx);
    }

    @Override
    protected void main() {
        ctx.gtpTask = ctx.gnbCtx.nts.findTask(NtsId.GNB_TASK_GTP);
        ctx.rrcTask = ctx.gnbCtx.nts.findTask(NtsId.GNB_TASK_RRC);

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
        ctx.gnbCtx.sim.findUe(msg.ueId).nts.findTask(NtsId.UE_TASK_MR).push(msg);
    }

    private void receiveUplinkData(IwUplinkData msg) {
        ctx.gtpTask.push(msg);
    }

    private void receiveDownlinkData(IwDownlinkData msg) {
        ctx.gnbCtx.sim.findUe(msg.ueId).nts.findTask(NtsId.UE_TASK_MR).push(msg);
    }
}
