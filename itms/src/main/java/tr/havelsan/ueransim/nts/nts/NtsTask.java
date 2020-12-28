/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nts.nts;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public abstract class NtsTask {

    private final BlockingDeque<Object> msgQueue;
    private final Thread thread;
    private final NtsScheduler scheduler;

    private boolean isStarted;

    public NtsTask() {
        this(false);
    }

    public NtsTask(boolean allocateScheduler) {
        this.msgQueue = new LinkedBlockingDeque<>();
        this.thread = new Thread(this::main);
        this.scheduler = allocateScheduler ? new NtsScheduler(this) : null;
    }

    protected abstract void main();

    public void start() {
        if (isStarted)
            throw new IllegalStateException("Task already started.");
        if (scheduler != null) {
            var schedulerThread = new Thread(scheduler::start);
            schedulerThread.start();
        }
        isStarted = true;
        thread.start();
    }

    public void releaseResources() {
        if (scheduler != null)
            scheduler.quit();

        // TODO: end the thread
        // TODO: clear the queue
    }

    // This method is usually not required, and it should be used with caution.
    public final Thread getThread() {
        return thread;
    }

    private Object takenMessage(Object obj) {
        if (obj instanceof INtsRunnable) {
            ((INtsRunnable) obj).run();
            return null;
        }
        return obj;
    }

    public void push(Object message) {
        msgQueue.addLast(message);
    }

    public void pushFront(Object message) {
        msgQueue.addLast(message);
    }

    public void push(INtsRunnable message) {
        push((Object) message);
    }

    public void pushFront(INtsRunnable message) {
        pushFront((Object) message);
    }

    public void pushDelayed(Object message, int ms) {
        if (scheduler == null) throw new IllegalStateException("This task does not created with a scheduler");
        scheduler.pushDelayed(message, ms);
    }

    public void pushFrontDelayed(Object message, int ms) {
        if (scheduler == null) throw new IllegalStateException("This task does not created with a scheduler");
        scheduler.pushFrontDelayed(message, ms);
    }

    public void pushDelayed(INtsRunnable message, int ms) {
        pushDelayed((Object) message, ms);
    }

    public void pushFrontDelayed(INtsRunnable message, int ms) {
        pushFrontDelayed((Object) message, ms);
    }

    public Object take() {
        return takenMessage(NtsUtils.take(msgQueue));
    }

    public Object poll() {
        return takenMessage(msgQueue.poll());
    }

    public Object poll(int timeout) {
        return takenMessage(NtsUtils.poll(msgQueue, timeout));
    }
}
