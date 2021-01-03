/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.ue.mr;

import tr.havelsan.ueransim.app.common.contexts.UeMrContext;
import tr.havelsan.ueransim.app.common.nts.*;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.nts.NtsId;
import tr.havelsan.ueransim.nts.nts.NtsTask;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;

public class UeMrTask extends NtsTask {

    private final UeMrContext ctx;

    public UeMrTask(UeSimContext ctx) {
        this.ctx = new UeMrContext(ctx);
    }

    @Override
    protected void main() {
        ctx.rrcTask = ctx.ueCtx.nts.findTask(NtsId.UE_TASK_RRC);
        ctx.appTask = ctx.ueCtx.nts.findTask(NtsId.UE_TASK_APP);

        while (true) {
            var msg = take();
            if (msg instanceof IwUplinkData) {
                receiveUplinkData((IwUplinkData) msg);
            } else if (msg instanceof IwDownlinkData) {
                receiveDownlinkData((IwDownlinkData) msg);
            } else if (msg instanceof IwPlmnSearchRequest) {
                receivePerformPlmnSearch((IwPlmnSearchRequest) msg);
            } else if (msg instanceof IwUplinkRrc) {
                receiveUplinkRrc((IwUplinkRrc) msg);
            } else if (msg instanceof IwDownlinkRrc) {
                receiveDownlinkRrc((IwDownlinkRrc) msg);
            }
        }
    }

    private void receiveUplinkData(IwUplinkData msg) {
        var gnb = ctx.ueCtx.sim.findGnbForUe(ctx.connectedGnb);
        if (gnb == null) {
            Log.error(Tag.FLOW, "Uplink Data transport failure: UE not connected to a gNB.");
        } else {
            gnb.nts.findTask(NtsId.GNB_TASK_MR).push(msg);
        }
    }

    private void receiveDownlinkData(IwDownlinkData msg) {
        ctx.appTask.push(msg);
    }

    private void receivePerformPlmnSearch(IwPlmnSearchRequest msg) {
        MrPlmnSearch.performPlmnSearch(ctx);
    }

    private void receiveUplinkRrc(IwUplinkRrc msg) {
        var gnb = ctx.ueCtx.sim.findGnbForUe(ctx.connectedGnb);
        if (gnb == null) {
            Log.error(Tag.FLOW, "Uplink RRC transport failure: UE not connected to a gNB.");
        } else {
            gnb.nts.findTask(NtsId.GNB_TASK_MR).push(msg);
        }
    }

    private void receiveDownlinkRrc(IwDownlinkRrc msg) {
        ctx.rrcTask.push(msg);
    }
}
