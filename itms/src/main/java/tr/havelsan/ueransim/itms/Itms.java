/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
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

    public Object receiveMessage(ItmsTask task, int timeout) {
        return task.receiveMessage(timeout);
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
