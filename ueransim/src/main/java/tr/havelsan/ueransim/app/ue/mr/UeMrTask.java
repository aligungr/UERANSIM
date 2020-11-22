/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue.mr;

import tr.havelsan.ueransim.app.common.itms.*;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.itms.nts.NtsTask;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;

import java.util.UUID;

public class UeMrTask extends NtsTask {

    private final UeSimContext ctx;

    private NtsTask nasTask;
    private NtsTask appTask;

    public UeMrTask(UeSimContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void main() {
        nasTask = ctx.nts.findTask(ItmsId.UE_TASK_NAS);
        appTask = ctx.nts.findTask(ItmsId.UE_TASK_APP);

        while (true) {
            var msg = take();
            if (msg instanceof IwDownlinkNas) {
                nasTask.push(msg);
            } else if (msg instanceof IwUplinkNas || msg instanceof IwUplinkData) {
                var gnb = ctx.sim.findGnbForUe(ctx.connectedGnb);
                if (gnb == null) {
                    Log.warning(Tag.FLOW, "Uplink NAS transport failure: UE not connected to a gNB.");
                } else {
                    gnb.nts.findTask(ItmsId.GNB_TASK_MR).push(msg);
                }
            } else if (msg instanceof IwDownlinkData) {
                appTask.push(msg);
            } else if (msg instanceof IwConnectionRelease) {
                nasTask.push(msg);
            } else if (msg instanceof IwPlmnSearchRequest) {
                performPlmnSearch();
            }
        }
    }

    private void performPlmnSearch() {
        UUID gnbId = null;
        // TODO: More complex mechanism to select a gNB.
        for (var gnb : ctx.sim.allGnbs()) {
            gnbId = gnb;
        }

        if (gnbId != null) {
            nasTask.push(new IwPlmnSearchResponse(gnbId));
        } else {
            Log.warning(Tag.FLOW, "No suitable gNB found for UE: %s", ctx.ueConfig.supi.toString());
        }
    }
}
