package com.runsim.backend.mts;

import com.runsim.backend.exceptions.IncorrectImplementationException;
import com.runsim.backend.utils.BiMap;

public final class TypeRegistry {
    private static final BiMap<String, Class<?>> types;

    static {
        types = new BiMap<>();
    }

    public static void register(String name, Class<?> type) {
        if (types.containsKey(name))
            throw new IncorrectImplementationException(name + " already exists");
        if (types.containsValue(type))
            throw new IncorrectImplementationException(type + " already exists");
        types.put(name, type);
    }

    public static Class<?> getClassByName(String name) {
        return types.getValue(name);
    }

    public static String getClassName(Class<?> type) {
        return types.getKey(type);
    }


}
