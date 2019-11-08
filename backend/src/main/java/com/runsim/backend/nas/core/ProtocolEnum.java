package com.runsim.backend.nas.core;

import com.runsim.backend.utils.Utils;

import java.lang.reflect.Modifier;

public class ProtocolEnum extends ProtocolValue {
    public final int value;
    public final String name;

    protected ProtocolEnum(int value, String name) {
        if (value < 0)
            throw new IllegalArgumentException("negative value");

        this.value = value;
        this.name = name;
    }

    protected static <T extends ProtocolEnum> T fromValueGeneric(Class<T> clazz, int value) {
        return fromValueGeneric(clazz, value, null);
    }

    protected static <T extends ProtocolEnum> T fromValueGeneric(Class<T> clazz, int value, T defaultValue) {
        var declaredFields = clazz.getDeclaredFields();
        for (var field : declaredFields) {
            if (!Modifier.isStatic(field.getModifiers())) continue;
            if (!clazz.isAssignableFrom(field.getType())) continue;

            T val;
            try {
                val = (T) field.get(null);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            if (val == null) continue;

            if (val.value == value)
                return val;
        }
        return defaultValue;
    }

    @Override
    public final boolean equals(Object obj) {
        return Utils.unsignedEqual(this, obj);
    }

    @Override
    public final int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return name;
    }
}
