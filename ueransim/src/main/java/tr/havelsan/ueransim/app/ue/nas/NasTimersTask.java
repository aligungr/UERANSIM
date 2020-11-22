/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue.nas;

import tr.havelsan.ueransim.app.common.itms.IwNasTimerExpire;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.itms.nts.NtsTask;
import tr.havelsan.ueransim.utils.Utils;

public class NasTimersTask extends NtsTask {

    private final UeSimContext ctx;
    private NtsTask nasTask;

    public NasTimersTask(UeSimContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void main() {
        nasTask = ctx.nts.findTask(ItmsId.UE_TASK_NAS);

        while (true) {
            // TODO: Sleep olmasında sakınca yok ama sleep olmadığında timer tik atmıyor, bug var.
            Utils.sleep(1500);

            var timers = ctx.ueTimers;

            if (timers.t3346.performTick()) sendExpireMsg(timers.t3346);
            if (timers.t3396.performTick()) sendExpireMsg(timers.t3396);
            if (timers.t3444.performTick()) sendExpireMsg(timers.t3444);
            if (timers.t3445.performTick()) sendExpireMsg(timers.t3445);
            if (timers.t3502.performTick()) sendExpireMsg(timers.t3502);
            if (timers.t3510.performTick()) sendExpireMsg(timers.t3510);
            if (timers.t3511.performTick()) sendExpireMsg(timers.t3511);
            if (timers.t3512.performTick()) sendExpireMsg(timers.t3512);
            if (timers.t3516.performTick()) sendExpireMsg(timers.t3516);
            if (timers.t3517.performTick()) sendExpireMsg(timers.t3517);
            if (timers.t3519.performTick()) sendExpireMsg(timers.t3519);
            if (timers.t3520.performTick()) sendExpireMsg(timers.t3520);
            if (timers.t3521.performTick()) sendExpireMsg(timers.t3521);
            if (timers.t3525.performTick()) sendExpireMsg(timers.t3525);
            if (timers.t3540.performTick()) sendExpireMsg(timers.t3540);
            if (timers.t3580.performTick()) sendExpireMsg(timers.t3580);
            if (timers.t3581.performTick()) sendExpireMsg(timers.t3581);
            if (timers.t3582.performTick()) sendExpireMsg(timers.t3582);
            if (timers.t3583.performTick()) sendExpireMsg(timers.t3583);
            if (timers.t3584.performTick()) sendExpireMsg(timers.t3584);
            if (timers.t3585.performTick()) sendExpireMsg(timers.t3585);
        }
    }

    private void sendExpireMsg(NasTimer timer) {
        nasTask.push(new IwNasTimerExpire(timer));
    }
}
