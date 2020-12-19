/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nts.scheduler;

import java.util.Comparator;

class ScheduleKeyComparator implements Comparator<ScheduleKey> {

    @Override
    public int compare(ScheduleKey a, ScheduleKey b) {
        if (a.time == b.time)
            return Long.compare(a.stabilizer, b.stabilizer);
        return Long.compare(a.time, b.time);
    }
}
