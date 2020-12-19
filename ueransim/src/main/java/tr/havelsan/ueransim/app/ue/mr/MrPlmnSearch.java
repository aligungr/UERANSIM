/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue.mr;

import tr.havelsan.ueransim.app.common.contexts.UeMrContext;
import tr.havelsan.ueransim.app.common.nts.IwPlmnSearchResponse;
import tr.havelsan.ueransim.app.common.nts.IwUeStatusUpdate;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;

import java.util.UUID;

public class MrPlmnSearch {

    public static void performPlmnSearch(UeMrContext ctx) {
        UUID gnbId = null;
        String gnbName = null;

        for (var gnb : ctx.ueCtx.sim.allGnbs()) {
            var gnbCtx = ctx.ueCtx.sim.findGnbForUe(gnb);
            if (gnbCtx != null) {
                gnbId = gnbCtx.ctxId;
                gnbName = gnbCtx.nodeName;
                break;
            }
        }

        if (gnbId != null) {
            ctx.connectedGnb = gnbId;
            ctx.connectedGnbName = gnbName;
            ctx.rrcTask.push(new IwPlmnSearchResponse(gnbId));

            var statusUpdate = new IwUeStatusUpdate(IwUeStatusUpdate.CONNECTED_GNB);
            statusUpdate.gnbName = gnbName;
            ctx.appTask.push(statusUpdate);
        } else {
            if (ctx.noPlmnWarning.check(60000)) {
                Log.warning(Tag.FLOW, "No suitable gNB found for UE: %s", ctx.ueCtx.ueConfig.supi.toString());
            }
        }
    }
}
