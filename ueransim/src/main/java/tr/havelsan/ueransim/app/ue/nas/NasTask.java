/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.ue.nas;

import tr.havelsan.ueransim.app.common.contexts.NasContext;
import tr.havelsan.ueransim.app.common.nts.*;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.app.ue.nas.mm.MobilityManagement;
import tr.havelsan.ueransim.app.ue.nas.sm.SessionManagement;
import tr.havelsan.ueransim.nas.NasDecoder;
import tr.havelsan.ueransim.nts.NtsId;
import tr.havelsan.ueransim.nts.nts.NtsTask;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;

import java.util.function.Consumer;

public class NasTask extends NtsTask {

    private static final int CYCLE_TIMER = 1;
    private static final int CYCLE_MM = 2;

    private static final int PERIOD_TIMER = 1000;
    private static final int PERIOD_MM = 100;

    private final NasContext ctx;

    public NasTask(UeSimContext ctx) {
        super(true);
        this.ctx = new NasContext(ctx);
    }

    @Override
    protected void main() {
        ctx.appTask = ctx.ueCtx.nts.findTask(NtsId.UE_TASK_APP);
        ctx.rrcTask = ctx.ueCtx.nts.findTask(NtsId.UE_TASK_RRC);

        pushDelayed(new IwPerformCycle(CYCLE_MM), PERIOD_MM);
        pushDelayed(new IwPerformCycle(CYCLE_TIMER), PERIOD_TIMER);

        var statusUpdate = new IwUeStatusUpdate(IwUeStatusUpdate.MM_STATE);
        statusUpdate.mmState = ctx.mmCtx.mmState;
        statusUpdate.mmSubState = ctx.mmCtx.mmSubState;
        ctx.appTask.push(statusUpdate);

        statusUpdate = new IwUeStatusUpdate(IwUeStatusUpdate.RM_STATE);
        statusUpdate.rmState = ctx.mmCtx.rmState;
        ctx.appTask.push(statusUpdate);

        while (true) {
            var msg = take();
            if (msg instanceof IwDownlinkNas) {
                receiveDownlinkNas((IwDownlinkNas) msg);
            } else if (msg instanceof IwNasTimerExpire) {
                receiveTimerExpire((IwNasTimerExpire) msg);
            } else if (msg instanceof IwUeExternalCommand) {
                receiveTestCommand((IwUeExternalCommand) msg);
            } else if (msg instanceof IwPlmnSearchResponse) {
                receivePlmnSearchResponse((IwPlmnSearchResponse) msg);
            } else if (msg instanceof IwPerformCycle) {
                receivePerformCycle((IwPerformCycle) msg);
            }
        }
    }

    private void receiveDownlinkNas(IwDownlinkNas msg) {
        NasTransport.receiveNas(ctx, NasDecoder.nasPdu(msg.nasPdu));
    }

    private void receiveTimerExpire(IwNasTimerExpire msg) {
        Log.info(Tag.TIMER, "NAS Timer expired: %s", msg.timer);

        if (msg.timer.isMmTimer) {
            MobilityManagement.receiveTimerExpire(ctx, msg.timer);
        } else {
            SessionManagement.receiveTimerExpire(ctx, msg.timer);
        }
    }

    private void receiveTestCommand(IwUeExternalCommand msg) {
        if (!MobilityManagement.executeCommand(ctx, msg.cmd)) {
            if (!SessionManagement.executeCommand(ctx, msg.cmd)) {
                Log.error(Tag.SYS, "invalid command: %s", msg.cmd);
            }
        }
    }

    private void receivePlmnSearchResponse(IwPlmnSearchResponse msg) {
        MobilityManagement.receiveNtsMessage(ctx, msg);
    }

    private void receivePerformCycle(IwPerformCycle msg) {
        if (msg.type == CYCLE_MM) {
            MobilityManagement.cycle(ctx);
            pushDelayed(msg, PERIOD_MM);
        } else if (msg.type == CYCLE_TIMER) {
            performTick();
            pushDelayed(msg, PERIOD_TIMER);
        }
    }

    private void performTick() {
        Consumer<NasTimer> sendExpireMsg = timer -> push(new IwNasTimerExpire(timer));

        var timers = ctx.ueTimers;

        if (timers.t3346.performTick()) sendExpireMsg.accept(timers.t3346);
        if (timers.t3396.performTick()) sendExpireMsg.accept(timers.t3396);
        if (timers.t3444.performTick()) sendExpireMsg.accept(timers.t3444);
        if (timers.t3445.performTick()) sendExpireMsg.accept(timers.t3445);
        if (timers.t3502.performTick()) sendExpireMsg.accept(timers.t3502);
        if (timers.t3510.performTick()) sendExpireMsg.accept(timers.t3510);
        if (timers.t3511.performTick()) sendExpireMsg.accept(timers.t3511);
        if (timers.t3512.performTick()) sendExpireMsg.accept(timers.t3512);
        if (timers.t3516.performTick()) sendExpireMsg.accept(timers.t3516);
        if (timers.t3517.performTick()) sendExpireMsg.accept(timers.t3517);
        if (timers.t3519.performTick()) sendExpireMsg.accept(timers.t3519);
        if (timers.t3520.performTick()) sendExpireMsg.accept(timers.t3520);
        if (timers.t3521.performTick()) sendExpireMsg.accept(timers.t3521);
        if (timers.t3525.performTick()) sendExpireMsg.accept(timers.t3525);
        if (timers.t3540.performTick()) sendExpireMsg.accept(timers.t3540);
        if (timers.t3580.performTick()) sendExpireMsg.accept(timers.t3580);
        if (timers.t3581.performTick()) sendExpireMsg.accept(timers.t3581);
        if (timers.t3582.performTick()) sendExpireMsg.accept(timers.t3582);
        if (timers.t3583.performTick()) sendExpireMsg.accept(timers.t3583);
        if (timers.t3584.performTick()) sendExpireMsg.accept(timers.t3584);
        if (timers.t3585.performTick()) sendExpireMsg.accept(timers.t3585);
    }
}
