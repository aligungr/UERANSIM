/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue.mr;

import tr.havelsan.ueransim.app.common.itms.*;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.itms.Itms;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.itms.ItmsTask;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;

import java.util.UUID;

public class MrTask extends ItmsTask {

    private final UeSimContext ctx;

    public MrTask(Itms itms, int taskId, UeSimContext ctx) {
        super(itms, taskId);
        this.ctx = ctx;
    }

    @Override
    public void main() {
        while (true) {
            var msg = itms.receiveMessage(this);
            if (msg instanceof IwDownlinkNas) {
                ctx.itms.sendMessage(ItmsId.UE_TASK_NAS, msg);
            } else if (msg instanceof IwUplinkNas || msg instanceof IwUplinkData) {
                var gnb = ctx.sim.findGnb(ctx.connectedGnb);
                if (gnb == null) {
                    Log.error(Tag.PROC, "Uplink NAS transport failure: UE not connected to a gNB.");
                } else {
                    gnb.itms.sendMessage(ItmsId.GNB_TASK_MR, msg);
                }
            } else if (msg instanceof IwDownlinkData) {
                ctx.itms.sendMessage(ItmsId.UE_TASK_APP, msg);
            } else if (msg instanceof IwConnectionRelease) {
                ctx.itms.sendMessage(ItmsId.UE_TASK_NAS, msg);
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
            ctx.itms.sendMessage(ItmsId.UE_TASK_NAS, new IwPlmnSearchResponse(gnbId));
        } else {
            Log.error(Tag.PROC, "No suitable gNB found for UE: %s", ctx.ueConfig.supi.toString());
        }
    }
}
