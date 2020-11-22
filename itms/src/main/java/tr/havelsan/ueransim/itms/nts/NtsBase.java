/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.itms.nts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NtsBase {

    private final HashMap<Integer, NtsTask> idMap;
    private final HashMap<Class<? extends NtsTask>, List<NtsTask>> typeMap;

    public NtsBase() {
        this.idMap = new HashMap<>();
        this.typeMap = new HashMap<>();
    }

    public void registerTask(int id, NtsTask task) {
        synchronized (this) {
            if (idMap.containsKey(id)) {
                throw new IllegalArgumentException("id already exists");
            }

            idMap.put(id, task);
            var set = typeMap.get(task.getClass());
            if (set == null) {
                set = new ArrayList<>();
            }
            set.add(task);
            typeMap.put(task.getClass(), set);
        }
    }

    // Usually it's better to call this once and hold a reference to the task.
    public NtsTask findTask(int id) {
        synchronized (this) {
            return idMap.get(id);
        }
    }

    // Usually it's better to call this once and hold a reference to the task.
    public <T extends NtsTask> T findTask(Class<T> type) {
        synchronized (this) {
            var set = typeMap.get(type);
            if (set == null || set.size() > 1)
                return null;
            return (T) set.get(0);
        }
    }
}
