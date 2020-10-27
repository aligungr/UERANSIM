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
 */

package tr.havelsan.ueransim.itms;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

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

    Object receiveMessageNonBlocking() {
        return msgQueue.poll();
    }

    private void onException(Exception e) {
        // TODO
    }
}
