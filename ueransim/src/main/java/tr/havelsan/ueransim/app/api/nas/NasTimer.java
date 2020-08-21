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

package tr.havelsan.ueransim.app.api.nas;

import tr.havelsan.ueransim.nas.impl.ies.IEGprsTimer2;
import tr.havelsan.ueransim.nas.impl.ies.IEGprsTimer3;
import tr.havelsan.ueransim.utils.Logging;
import tr.havelsan.ueransim.utils.Tag;

public class NasTimer {
    public final int timerCode;
    public final boolean isMmTimer;

    private int interval;
    private long startMillis;
    private boolean isRunning;

    private long _lastDebugPrintMs;

    public NasTimer(int timerCode, boolean isMmTimer, int defaultInterval) {
        this.timerCode = timerCode;
        this.isMmTimer = isMmTimer;
        this.interval = defaultInterval;
    }

    public void start() {
        startMillis = System.currentTimeMillis();
        isRunning = true;

        Logging.debug(Tag.NAS_TIMER, "NAS Timer %s started with interval: %ss", timerCode, interval);
    }

    public void start(IEGprsTimer2 v) {
        if (!v.hasValue()) {
            Logging.warning(Tag.NAS_TIMER, "NAS Timer %s start called but no value provided", timerCode);
            return;
        }

        interval = v.value.intValue();
        startMillis = System.currentTimeMillis();
        isRunning = true;

        Logging.debug(Tag.NAS_TIMER, "NAS Timer %s started with interval: %ss", timerCode, interval);
    }

    public void start(IEGprsTimer3 v) {
        if (!v.hasValue()) {
            Logging.warning(Tag.NAS_TIMER, "NAS Timer %s start called but no value provided", timerCode);
            return;
        }

        var secs = 0;
        int val = v.timerValue.intValue();

        if (v.unit == IEGprsTimer3.EGprsTimerValueUnit3.MULTIPLES_OF_2SEC) secs = val * 2;
        else if (v.unit == IEGprsTimer3.EGprsTimerValueUnit3.MULTIPLES_OF_1MIN) secs = val * 60;
        else if (v.unit == IEGprsTimer3.EGprsTimerValueUnit3.MULTIPLES_OF_10MIN) secs = val * 60 * 10;
        else if (v.unit == IEGprsTimer3.EGprsTimerValueUnit3.MULTIPLES_OF_1HOUR) secs = val * 60 * 60;
        else if (v.unit == IEGprsTimer3.EGprsTimerValueUnit3.MULTIPLES_OF_10HOUR) secs = val * 60 * 60 * 10;
        else if (v.unit == IEGprsTimer3.EGprsTimerValueUnit3.MULTIPLES_OF_30SEC) secs = val * 30;
        else if (v.unit == IEGprsTimer3.EGprsTimerValueUnit3.MULTIPLES_OF_320HOUR) secs = val * 60 * 60 * 320;

        interval = secs;
        startMillis = System.currentTimeMillis();
        isRunning = true;

        Logging.debug(Tag.NAS_TIMER, "NAS Timer %s started with interval: %ss", timerCode, interval);
    }

    public void stop() {
        if (isRunning) {
            startMillis = System.currentTimeMillis();
            isRunning = false;

            Logging.debug(Tag.NAS_TIMER, "NAS Timer %s stopped", timerCode);
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean performTick() {
        if (isRunning) {
            long currentMs = System.currentTimeMillis();
            long deltaSec = (currentMs - startMillis) / 1000;
            long remainingSec = interval - deltaSec;

            if (currentMs - _lastDebugPrintMs > 10 * 1000) {
                _lastDebugPrintMs = currentMs;
                Logging.debug(Tag.NAS_TIMER, "NAS Timer %s int:%ss rem:%ss", timerCode, interval, remainingSec);
            }

            if (remainingSec < 0) {
                stop();
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "NasTimer{" +
                "timerCode=" + timerCode +
                '}';
    }
}
