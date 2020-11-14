/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.itms;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public abstract class ItmsTask {

    public final int taskId;
    public final BlockingQueue<Object> msgQueue;
    public final Thread thread;
    protected final Itms itms;

    private boolean isStarted;

    protected ItmsTask(Itms itms, int taskId) {
        this.itms = itms;
        this.taskId = taskId;
        this.msgQueue = new LinkedBlockingQueue<>();
        this.thread = new Thread(() -> {
            try {
                main();
            } catch (Exception e) {
                onException(e);
                throw e;
            }
        });
    }

    public abstract void main();

    void start() {
        if (isStarted)
            throw new IllegalStateException("ITMS task already started");
        isStarted = true;
        thread.start();
    }

    void putMessage(Object msg) {
        msgQueue.add(msg);
    }

    Object receiveMessage() {
        try {
            return msgQueue.take();
        } catch (InterruptedException e) {
            onException(e);
            throw new RuntimeException(e);
        }
    }

    Object receiveMessage(int timeout) {
        try {
            return msgQueue.poll(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            onException(e);
            throw new RuntimeException(e);
        }
    }

    Object receiveMessageNonBlocking() {
        return msgQueue.poll();
    }

    private void onException(Exception e) {
        // TODO
    }
}
