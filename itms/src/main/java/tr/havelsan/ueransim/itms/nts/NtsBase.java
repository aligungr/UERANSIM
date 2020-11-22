/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.itms.nts;

import java.util.HashMap;

public class NtsBase {

    private final HashMap<Integer, NtsTask> idMap;

    public NtsBase() {
        this.idMap = new HashMap<>();
    }

    public void registerTask(int id, NtsTask task) {
        synchronized (this) {
            if (idMap.containsKey(id)) {
                throw new IllegalArgumentException("id already exists");
            }
            idMap.put(id, task);
        }
    }

    // Usually it's better to call this once and hold a reference to the task.
    public NtsTask findTask(int id) {
        synchronized (this) {
            return idMap.get(id);
        }
    }

    // This method is just a syntactic sugar for real 'findTask'
    // Usually it's better to call this once and hold a reference to the task.
    public <T extends NtsTask> T findTask(int id, Class<T> type) {
        return (T) findTask(id);
    }
}
