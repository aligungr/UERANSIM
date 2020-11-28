/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue.rrc;

import tr.havelsan.ueransim.app.common.contexts.UeRrcContext;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.itms.nts.NtsTask;

public class RrcTask extends NtsTask {

    private final UeRrcContext ctx;

    public RrcTask(UeSimContext ueCtx) {
        this.ctx = new UeRrcContext(ueCtx);
    }

    @Override
    public void main() {
        ctx.mrTask = ctx.ueCtx.nts.findTask(ItmsId.UE_TASK_MR);
    }
}
