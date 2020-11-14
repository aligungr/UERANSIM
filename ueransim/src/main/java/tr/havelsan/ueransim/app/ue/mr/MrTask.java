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
            } else if (msg instanceof IwUplinkNas) {
                ctx.sim.findGnb(ctx.connectedGnb).itms.sendMessage(ItmsId.GNB_TASK_MR, msg);
            } else if (msg instanceof IwUplinkData) {
                ctx.sim.findGnb(ctx.connectedGnb).itms.sendMessage(ItmsId.GNB_TASK_MR, msg);
            } else if (msg instanceof IwDownlinkData) {
                ctx.itms.sendMessage(ItmsId.UE_TASK_APP, msg);
            } else if (msg instanceof IwConnectionRelease) {
                ctx.itms.sendMessage(ItmsId.UE_TASK_NAS, msg);
            }
        }
    }
}
