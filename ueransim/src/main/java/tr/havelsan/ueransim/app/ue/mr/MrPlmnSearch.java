/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue.mr;

import tr.havelsan.ueransim.app.common.contexts.UeMrContext;
import tr.havelsan.ueransim.app.common.itms.IwPlmnSearchResponse;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;

import java.util.UUID;

public class MrPlmnSearch {

    public static void performPlmnSearch(UeMrContext ctx) {
        UUID gnbId = null;
        // TODO: More complex mechanism to select a gNB.
        for (var gnb : ctx.ueCtx.sim.allGnbs()) {
            gnbId = gnb;
        }

        if (gnbId != null) {
            ctx.connectedGnb = gnbId;
            ctx.rrcTask.push(new IwPlmnSearchResponse(gnbId));
        } else {
            Log.warning(Tag.FLOW, "No suitable gNB found for UE: %s", ctx.ueCtx.ueConfig.supi.toString());
        }
    }
}
