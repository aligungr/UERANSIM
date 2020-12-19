/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nts.scheduler;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

class ScheduleKey {
    private static final AtomicLong counter = new AtomicLong();

    public final long time;
    public final long stabilizer;

    public ScheduleKey(long time) {
        this.time = time;
        this.stabilizer = counter.incrementAndGet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleKey that = (ScheduleKey) o;
        return time == that.time && stabilizer == that.stabilizer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, stabilizer);
    }
}
