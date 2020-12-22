/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.link.rlc.utils;

public class RlcTimer {
    private final long period;
    private long startedAt; // 0 means not started

    public RlcTimer(long period) {
        this.period = period;
        this.startedAt = 0;
    }

    // Returns true iff the timer was running and just expired and has been reset.
    public boolean cycle(long currentTime) {
        // If timer is running and expired
        if (startedAt != 0 && currentTime > startedAt + period) {
            // Stop timer
            startedAt = 0;
            // Handle expire actions
            return true;
        }

        return false;
    }

    public void start(long currentTime) {
        startedAt = currentTime;
    }

    public void stop() {
        startedAt = 0;
    }

    public boolean isRunning() {
        return startedAt != 0;
    }

    public boolean stoppedOrExpired(long currentTime) {
        return !isRunning() || currentTime - startedAt > period;
    }
}
