/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.ue.nas;

import tr.havelsan.ueransim.nas.impl.ies.IEGprsTimer2;
import tr.havelsan.ueransim.nas.impl.ies.IEGprsTimer3;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;

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

        Log.debug(Tag.TIMER, "NAS Timer %s started with interval: %ss", timerCode, interval);
    }

    public void start(IEGprsTimer2 v) {
        if (!v.hasValue()) {
            Log.warning(Tag.TIMER, "NAS Timer %s start called but no value provided", timerCode);
            return;
        }

        interval = v.value.intValue();
        startMillis = System.currentTimeMillis();
        isRunning = true;

        Log.debug(Tag.TIMER, "NAS Timer %s started with interval: %ss", timerCode, interval);
    }

    public void start(IEGprsTimer3 v) {
        if (!v.hasValue()) {
            Log.warning(Tag.TIMER, "NAS Timer %s start called but no value provided", timerCode);
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

        Log.debug(Tag.TIMER, "NAS Timer %s started with interval: %ss", timerCode, interval);
    }

    public void stop() {
        if (isRunning) {
            startMillis = System.currentTimeMillis();
            isRunning = false;

            Log.debug(Tag.TIMER, "NAS Timer %s stopped", timerCode);
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
                Log.debug(Tag.TIMER, "NAS Timer %s int:%ss rem:%ss", timerCode, interval, remainingSec);
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
                ", interval=" + interval +
                '}';
    }
}
