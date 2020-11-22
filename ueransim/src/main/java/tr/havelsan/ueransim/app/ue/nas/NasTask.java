/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue.nas;

import tr.havelsan.ueransim.app.common.itms.*;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.app.common.testcmd.TestCmd;
import tr.havelsan.ueransim.app.ue.mm.MobilityManagement;
import tr.havelsan.ueransim.app.ue.sm.SessionManagement;
import tr.havelsan.ueransim.itms.nts.NtsTask;
import tr.havelsan.ueransim.nas.NasDecoder;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;

public class NasTask extends NtsTask {

    private final UeSimContext ctx;

    public NasTask(UeSimContext ctx) {
        this.ctx = ctx;
    }

    private static void executeCommand(UeSimContext ctx, TestCmd cmd) {
        if (!MobilityManagement.executeCommand(ctx, cmd)) {
            if (!SessionManagement.executeCommand(ctx, cmd)) {
                Log.error(Tag.SYS, "invalid command: %s", cmd);
            }
        }
    }

    @Override
    public void main() {
        // TODO: Make this task sleepable/wakeable like other tasks.
        while (true) {
            MobilityManagement.cycle(ctx);

            var msg = poll();
            if (msg instanceof IwDownlinkNas) {
                NasTransport.receiveNas(ctx, NasDecoder.nasPdu(((IwDownlinkNas) msg).nasPdu));
            } else if (msg instanceof IwNasTimerExpire) {
                var timer = ((IwNasTimerExpire) msg).timer;
                Log.info(Tag.TIMER, "NAS Timer expired: %s", timer);

                if (timer.isMmTimer) {
                    MobilityManagement.receiveTimerExpire(ctx, timer);
                } else {
                    SessionManagement.receiveTimerExpire(ctx, timer);
                }
            } else if (msg instanceof IwUeTestCommand) {
                executeCommand(ctx, ((IwUeTestCommand) msg).cmd);
            } else if (msg instanceof IwConnectionRelease) {
                // TODO
            } else if (msg instanceof IwPlmnSearchResponse) {
                MobilityManagement.receiveItmsMessage(ctx, msg);
            }
        }
    }
}
