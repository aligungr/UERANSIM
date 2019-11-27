package com.runsim.backend.nas.core;

import com.runsim.backend.exceptions.ReservedOrInvalidValueException;
import com.runsim.backend.utils.Utils;

import java.lang.reflect.Modifier;

public class ProtocolEnum extends ProtocolValue {
    protected final int value;
    protected final String name;

    protected ProtocolEnum(int value, String name) {
        if (value < 0)
            throw new IllegalArgumentException("negative value");

        this.value = value;
        this.name = name;
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

        if (defaultValue != null)
            return defaultValue;
        throw new ReservedOrInvalidValueException(clazz.getSimpleName(), value);
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

    public int intValue() {
        return value;
    }

    public String name() {
        return name;
    }
}
