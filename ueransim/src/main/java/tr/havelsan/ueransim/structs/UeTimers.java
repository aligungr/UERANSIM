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
 *
 * @author Ali Güngör (aligng1620@gmail.com)
 */

package tr.havelsan.ueransim.structs;

import tr.havelsan.ueransim.api.nas.NasTimer;
import tr.havelsan.ueransim.core.UeSimContext;
import tr.havelsan.ueransim.events.ue.UeTimerExpireEvent;

public class UeTimers {
    public final NasTimer t3346; /* MM - ... */
    public final NasTimer t3396; /* SM - ... */

    public final NasTimer t3444; /* MM - ... */
    public final NasTimer t3445; /* MM - ... */

    public final NasTimer t3502; /* MM - ... */
    public final NasTimer t3510; /* MM - Registration Request transmission timer */
    public final NasTimer t3511; /* MM - ... */
    public final NasTimer t3512; /* MM - Periodic registration update timer */
    public final NasTimer t3516; /* MM - 5G AKA - RAND and RES* storing timer */
    public final NasTimer t3517; /* MM - Service Request transmission timer */
    public final NasTimer t3519; /* MM - Transmission with fresh SUCI timer */
    public final NasTimer t3520; /* MM - ... */
    public final NasTimer t3521; /* MM - De-registration transmission timer for not switch off */
    public final NasTimer t3525; /* MM - ... */
    public final NasTimer t3540; /* MM - ... */

    public final NasTimer t3580; /* SM - ... */
    public final NasTimer t3581; /* SM - ... */
    public final NasTimer t3582; /* SM - ... */
    public final NasTimer t3583; /* SM - ... */
    public final NasTimer t3584; /* SM - ... */
    public final NasTimer t3585; /* SM - ... */

    public UeTimers() {
        this.t3346 = new NasTimer(3346, true, Integer.MAX_VALUE);
        this.t3396 = new NasTimer(3396, false, Integer.MAX_VALUE);
        this.t3444 = new NasTimer(3444, true, 12 * 60 * 60);
        this.t3445 = new NasTimer(3445, true, 12 * 60 * 60);
        this.t3502 = new NasTimer(3502, true, 12 * 60);
        this.t3510 = new NasTimer(3510, true, 15);
        this.t3511 = new NasTimer(3511, true, 10);
        this.t3512 = new NasTimer(3512, true, 54 * 60);
        this.t3516 = new NasTimer(3516, true, 30);
        this.t3517 = new NasTimer(3517, true, 15);
        this.t3519 = new NasTimer(3519, true, 60);
        this.t3520 = new NasTimer(3520, true, 15);
        this.t3521 = new NasTimer(3521, true, 15);
        this.t3525 = new NasTimer(3525, true, 60);
        this.t3540 = new NasTimer(3540, true, 10);
        this.t3580 = new NasTimer(3580, false, 16);
        this.t3581 = new NasTimer(3581, false, 16);
        this.t3582 = new NasTimer(3582, false, 16);
        this.t3583 = new NasTimer(3583, false, 60);
        this.t3584 = new NasTimer(3584, false, Integer.MAX_VALUE);
        this.t3585 = new NasTimer(3585, false, Integer.MAX_VALUE);
    }

    public void performTick(UeSimContext ctx) {
        if (t3346.performTick()) ctx.pushEvent(new UeTimerExpireEvent(t3346));
        if (t3396.performTick()) ctx.pushEvent(new UeTimerExpireEvent(t3396));
        if (t3444.performTick()) ctx.pushEvent(new UeTimerExpireEvent(t3444));
        if (t3445.performTick()) ctx.pushEvent(new UeTimerExpireEvent(t3445));
        if (t3502.performTick()) ctx.pushEvent(new UeTimerExpireEvent(t3502));
        if (t3510.performTick()) ctx.pushEvent(new UeTimerExpireEvent(t3510));
        if (t3511.performTick()) ctx.pushEvent(new UeTimerExpireEvent(t3511));
        if (t3512.performTick()) ctx.pushEvent(new UeTimerExpireEvent(t3512));
        if (t3516.performTick()) ctx.pushEvent(new UeTimerExpireEvent(t3516));
        if (t3517.performTick()) ctx.pushEvent(new UeTimerExpireEvent(t3517));
        if (t3519.performTick()) ctx.pushEvent(new UeTimerExpireEvent(t3519));
        if (t3520.performTick()) ctx.pushEvent(new UeTimerExpireEvent(t3520));
        if (t3521.performTick()) ctx.pushEvent(new UeTimerExpireEvent(t3521));
        if (t3525.performTick()) ctx.pushEvent(new UeTimerExpireEvent(t3525));
        if (t3540.performTick()) ctx.pushEvent(new UeTimerExpireEvent(t3540));
        if (t3580.performTick()) ctx.pushEvent(new UeTimerExpireEvent(t3580));
        if (t3581.performTick()) ctx.pushEvent(new UeTimerExpireEvent(t3581));
        if (t3582.performTick()) ctx.pushEvent(new UeTimerExpireEvent(t3582));
        if (t3583.performTick()) ctx.pushEvent(new UeTimerExpireEvent(t3583));
        if (t3584.performTick()) ctx.pushEvent(new UeTimerExpireEvent(t3584));
        if (t3585.performTick()) ctx.pushEvent(new UeTimerExpireEvent(t3585));
    }
}
