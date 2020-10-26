/*
 * MIT License
 *
 * Copyright (c) 2020 ALİ GÜNGÖR
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package tr.havelsan.ueransim.app.ue.nas;

import tr.havelsan.ueransim.app.common.itms.IwNasTimerExpire;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.itms.Itms;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.itms.ItmsTask;
import tr.havelsan.ueransim.utils.Utils;

public class NasTimersTask extends ItmsTask {

    private final UeSimContext ctx;

    public NasTimersTask(Itms itms, int taskId, UeSimContext ctx) {
        super(itms, taskId);
        this.ctx = ctx;
    }

    @Override
    public void main() {
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
        ctx.itms.sendMessage(ItmsId.UE_TASK_NAS, new IwNasTimerExpire(timer));
    }
}
