package com.runsim.backend.nas.core;

import com.runsim.backend.nas.types.ExtendedProtocolDiscriminator;

import java.lang.reflect.Modifier;

public class NasEnum extends NasValue {
    private final int value;
    private final String name;

    protected NasEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public final int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public final boolean equals(Object obj) {
        if (!(obj instanceof ExtendedProtocolDiscriminator))
            return false;
        return ((ExtendedProtocolDiscriminator) obj).getValue() == value;
    }

    @Override
    public final int hashCode() {
        return getValue();
    }

    @Override
    public final String toString() {
        return getName();
    }

    protected static <T extends NasEnum> T fromValueGeneric(Class<T> clazz, int value) {
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

            if (val.getValue() == value)
                return val;
        }
        return null;
    }
}
