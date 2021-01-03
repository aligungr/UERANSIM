/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nts.nts;

import tr.havelsan.ueransim.nts.scheduler.ScheduledObject;
import tr.havelsan.ueransim.nts.scheduler.Scheduler;

public class NtsScheduler extends Scheduler {

    private static final int PUSH_BACK = 1;
    private static final int PUSH_FRONT = 2;

    private final NtsTask task;

    public NtsScheduler(NtsTask task) {
        this.task = task;
    }

    public void pushDelayed(Object message, int ms) {
        scheduleDelayed(ms, new ScheduledObject(PUSH_BACK, 0, message));
    }

    public void pushFrontDelayed(Object message, int ms) {
        scheduleDelayed(ms, new ScheduledObject(PUSH_FRONT, 0, message));
    }

    @Override
    protected void onStart() {

    }

    @Override
    protected void onLoop() {

    }

    @Override
    protected void onConsume(ScheduledObject obj) {
        if (obj.arg0 == PUSH_BACK) {
            task.push(obj.obj);
        } else if (obj.arg0 == PUSH_FRONT) {
            task.pushFront(obj.obj);
        }
    }

    @Override
    protected void onQuit() {

    }
}
