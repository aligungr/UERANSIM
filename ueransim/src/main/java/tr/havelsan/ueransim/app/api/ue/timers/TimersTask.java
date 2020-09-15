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

package tr.havelsan.ueransim.app.api.ue.timers;

import tr.havelsan.itms.Itms;
import tr.havelsan.itms.ItmsTask;
import tr.havelsan.ueransim.app.core.UeSimContext;
import tr.havelsan.ueransim.app.events.ue.UeTimerExpireEvent;

public class TimersTask extends ItmsTask {

    private final UeSimContext ctx;

    public TimersTask(Itms itms, int taskId, UeSimContext ctx) {
        super(itms, taskId);
        this.ctx = ctx;
    }

    @Override
    public void main() {
        while (true) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            var timers = ctx.ueTimers;

            if (timers.t3346.performTick()) ctx.pushEvent(new UeTimerExpireEvent(timers.t3346));
            if (timers.t3396.performTick()) ctx.pushEvent(new UeTimerExpireEvent(timers.t3396));
            if (timers.t3444.performTick()) ctx.pushEvent(new UeTimerExpireEvent(timers.t3444));
            if (timers.t3445.performTick()) ctx.pushEvent(new UeTimerExpireEvent(timers.t3445));
            if (timers.t3502.performTick()) ctx.pushEvent(new UeTimerExpireEvent(timers.t3502));
            if (timers.t3510.performTick()) ctx.pushEvent(new UeTimerExpireEvent(timers.t3510));
            if (timers.t3511.performTick()) ctx.pushEvent(new UeTimerExpireEvent(timers.t3511));
            if (timers.t3512.performTick()) ctx.pushEvent(new UeTimerExpireEvent(timers.t3512));
            if (timers.t3516.performTick()) ctx.pushEvent(new UeTimerExpireEvent(timers.t3516));
            if (timers.t3517.performTick()) ctx.pushEvent(new UeTimerExpireEvent(timers.t3517));
            if (timers.t3519.performTick()) ctx.pushEvent(new UeTimerExpireEvent(timers.t3519));
            if (timers.t3520.performTick()) ctx.pushEvent(new UeTimerExpireEvent(timers.t3520));
            if (timers.t3521.performTick()) ctx.pushEvent(new UeTimerExpireEvent(timers.t3521));
            if (timers.t3525.performTick()) ctx.pushEvent(new UeTimerExpireEvent(timers.t3525));
            if (timers.t3540.performTick()) ctx.pushEvent(new UeTimerExpireEvent(timers.t3540));
            if (timers.t3580.performTick()) ctx.pushEvent(new UeTimerExpireEvent(timers.t3580));
            if (timers.t3581.performTick()) ctx.pushEvent(new UeTimerExpireEvent(timers.t3581));
            if (timers.t3582.performTick()) ctx.pushEvent(new UeTimerExpireEvent(timers.t3582));
            if (timers.t3583.performTick()) ctx.pushEvent(new UeTimerExpireEvent(timers.t3583));
            if (timers.t3584.performTick()) ctx.pushEvent(new UeTimerExpireEvent(timers.t3584));
            if (timers.t3585.performTick()) ctx.pushEvent(new UeTimerExpireEvent(timers.t3585));
        }
    }
}
