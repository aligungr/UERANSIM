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

import java.util.concurrent.ConcurrentHashMap;

public class Itms {

    private final ConcurrentHashMap<Integer, ItmsTask> taskMap;

    public Itms() {
        this.taskMap = new ConcurrentHashMap<>();
    }

    public void createTask(ItmsTask task) {
        // TODO: statements together are not atomic
        if (taskMap.containsKey(task.taskId))
            throw new IllegalStateException("task id already exists");
        taskMap.put(task.taskId, task);
    }

    public void startTask(ItmsTask task) {
        task.start();
    }

    public Object receiveMessage(ItmsTask task) {
        return task.receiveMessage();
    }

    public Object receiveMessageNonBlocking(ItmsTask task) {
        return task.receiveMessageNonBlocking();
    }

    public void sendMessage(ItmsTask task, Object msg) {
        task.putMessage(msg);
    }

    public void sendMessage(int taskId, Object msg) {
        var task = taskMap.get(taskId);
        if (task == null)
            throw new IllegalStateException("task id not found");
        sendMessage(task, msg);
    }

    public ItmsTask findTask(int taskId) {
        return taskMap.get(taskId);
    }
}
