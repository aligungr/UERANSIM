/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue.nas;

import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.nas.impl.ies.IEGprsTimer2;
import tr.havelsan.ueransim.nas.impl.ies.IEGprsTimer3;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;

public class NasTimer {
    public final UeSimContext ctx;
    public final int timerCode;
    public final boolean isMmTimer;

    private int interval;
    private long startMillis;
    private boolean isRunning;

    private long _lastDebugPrintMs;

    public NasTimer(UeSimContext ctx, int timerCode, boolean isMmTimer, int defaultInterval) {
        this.ctx = ctx;
        this.timerCode = timerCode;
        this.isMmTimer = isMmTimer;
        this.interval = defaultInterval;
    }

    public synchronized void start() {
        startMillis = System.currentTimeMillis();
        isRunning = true;

        Log.debug(Tag.TIMER, "NAS Timer %s started with interval: %ss", timerCode, interval);
    }

    public synchronized void start(IEGprsTimer2 v) {
        if (!v.hasValue()) {
            Log.warning(Tag.TIMER, "NAS Timer %s start called but no value provided", timerCode);
            return;
        }

        interval = v.value.intValue();
        startMillis = System.currentTimeMillis();
        isRunning = true;

        Log.debug(Tag.TIMER, "NAS Timer %s started with interval: %ss", timerCode, interval);
    }

    public synchronized void start(IEGprsTimer3 v) {
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

    public synchronized void stop() {
        if (isRunning) {
            startMillis = System.currentTimeMillis();
            isRunning = false;

            Log.debug(Tag.TIMER, "NAS Timer %s stopped", timerCode);
        }
    }

    public synchronized boolean isRunning() {
        return isRunning;
    }

    public synchronized boolean performTick() {
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
    public synchronized String toString() {
        return "NasTimer{" +
                "timerCode=" + timerCode +
                '}';
    }
}
