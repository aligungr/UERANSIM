/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.core;

import tr.havelsan.ueransim.utils.IIntValue;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.exceptions.ReservedOrInvalidValueException;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ProtocolEnum extends ProtocolValue implements IIntValue {
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
            if (!Modifier.isPublic(field.getModifiers())) continue;
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

    public static <T extends ProtocolEnum> T fromIntValue(Class<T> clazz, int value) {
        Object res;
        try {
            var method = clazz.getDeclaredMethod("fromValue", int.class);
            res = method.invoke(null, value);
        } catch (Exception e) {
            throw new ReservedOrInvalidValueException(clazz.getSimpleName(), value);
        }
        return (T) res;
    }

    public static <T extends ProtocolEnum> T fromIdentifier(Class<T> clazz, String identifier) {
        var declaredFields = clazz.getDeclaredFields();
        for (var field : declaredFields) {
            if (!Modifier.isStatic(field.getModifiers())) continue;
            if (!Modifier.isPublic(field.getModifiers())) continue;
            if (!clazz.isAssignableFrom(field.getType())) continue;

            if (field.getName().equals(identifier)) {
                T val;
                try {
                    val = (T) field.get(null);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                return val;
            }
        }
        return null;
    }

    public static <T extends ProtocolEnum> List<T> fromName(Class<T> clazz, String name) {
        var res = new ArrayList<T>();

        var declaredFields = clazz.getDeclaredFields();
        for (var field : declaredFields) {
            if (!Modifier.isStatic(field.getModifiers())) continue;
            if (!Modifier.isPublic(field.getModifiers())) continue;
            if (!clazz.isAssignableFrom(field.getType())) continue;

            T val;
            try {
                val = (T) field.get(null);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            if (val == null) continue;

            if (val.name.equals(name))
                res.add(val);
        }

        return res;
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
