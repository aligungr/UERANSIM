package com.runsim.backend.protocols.core;

import com.runsim.backend.protocols.bits.BitN;
import com.runsim.backend.protocols.octets.Octet;
import com.runsim.backend.protocols.octets.Octet2;

import java.lang.reflect.Modifier;

public class ProtocolEnum extends ProtocolValue {
    public final int value;
    public final String name;

    protected ProtocolEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    protected static <T extends ProtocolEnum> T fromValueGeneric(Class<T> clazz, int value) {
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
        return null;
    }

    @Deprecated
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
        if (obj instanceof Integer)
            return (Integer) obj == value;
        if (obj instanceof BitN)
            return ((BitN) obj).intValue == value;
        if (obj instanceof Octet)
            return ((Octet) obj).intValue == value;
        if (obj instanceof Octet2)
            return ((Octet2) obj).intValue == value;
        if (!(obj instanceof ProtocolEnum))
            return false;
        return ((ProtocolEnum) obj).value == value;
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
