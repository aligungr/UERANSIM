/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nts.scheduler;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Scheduler {

    private final TreeMap<ScheduleKey, ScheduledObject> map;
    private final Object mutex;
    private final AtomicBoolean isStarted;
    private final AtomicBoolean isQuited;

    private boolean isRunning;

    public Scheduler() {
        this.map = new TreeMap<>(new ScheduleKeyComparator());
        this.mutex = new Object();
        this.isStarted = new AtomicBoolean();
        this.isQuited = new AtomicBoolean();
    }

    private void mainLoop() throws InterruptedException {
        while (isRunning) {
            onLoop();

            Map.Entry<ScheduleKey, ScheduledObject> item;

            synchronized (map) {
                item = map.pollFirstEntry();
            }

            if (item == null) {
                synchronized (mutex) {
                    mutex.wait();
                }
            } else {
                long delta = item.getKey().time - System.currentTimeMillis();
                if (delta <= 0) {
                    onConsume(item.getValue());
                } else {
                    synchronized (map) {
                        map.put(item.getKey(), item.getValue());
                    }
                    synchronized (mutex) {
                        mutex.wait(delta);
                    }
                }
            }
        }
    }

    public void start() {
        if (isStarted.getAndSet(true))
            throw new RuntimeException("can only start once");
        isRunning = true;
        onStart();
        try {
            mainLoop();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        onQuit();
    }

    public void scheduleDelayed(long ms, ScheduledObject obj) {
        scheduleExact(System.currentTimeMillis() + ms, obj);
    }

    public void scheduleExact(long time, ScheduledObject obj) {
        if (isQuited.get()) {
            // throw new IllegalStateException("Scheduler quited.");
            return;
        }

        var key = new ScheduleKey(time);

        synchronized (map) {
            map.put(key, obj);
        }
        synchronized (mutex) {
            mutex.notify();
        }
    }

    public void quit() {
        isRunning = false;
        isQuited.set(true);
        mutex.notify();
    }

    protected abstract void onStart();

    protected abstract void onLoop();

    protected abstract void onConsume(ScheduledObject obj);

    protected abstract void onQuit();
}
