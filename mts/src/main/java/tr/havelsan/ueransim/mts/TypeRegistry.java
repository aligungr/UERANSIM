/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.mts;

import tr.havelsan.ueransim.utils.BiMap;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.exceptions.IncorrectImplementationException;
import tr.havelsan.ueransim.utils.octets.*;

import java.util.*;

public final class TypeRegistry {
    static final boolean ALLOW_DEEP_CONVERSION = false;

    private final BiMap<String, Class<?>> types;
    private final Map<Class<?>, ICustomConstruct<?>> customConstructs;
    private final Set<ICustomIsConvertable> customIsConvertables;
    private final Map<Class<?>, ICustomConverter<?>> customConverters;

    public TypeRegistry() {
        types = new BiMap<>();
        customConstructs = new HashMap<>();
        customIsConvertables = new HashSet<>();
        customConverters = new HashMap<>();

        var coreTypes = new Class[]{
                Bit.class,
                Bit2.class,
                Bit3.class,
                Bit4.class,
                Bit5.class,
                Bit6.class,
                Bit7.class,
                Bit8.class,
                Bit9.class,
                Bit10.class,
                Bit11.class,
                Bit12.class,
                Octet.class,
                Octet2.class,
                Octet3.class,
                Octet4.class,
                Octet5.class,
                Octet6.class,
                Octet7.class,
                OctetString.class,
        };

        for (var type : coreTypes)
            registerTypeName(type.getSimpleName(), type);
    }

    public void registerTypeName(String name, Class<?> type) {
        if (types.containsKey(name))
            throw new IncorrectImplementationException(name + " already exists");
        if (types.containsValue(type))
            throw new IncorrectImplementationException(type + " already exists");
        types.put(name, type);
    }

    private <T> void registerCustomConstruct(Class<T> type, ICustomConstruct<T> constructor) {
        if (customConstructs.containsKey(type))
            throw new IncorrectImplementationException(type + " already exists");
        for (var clazz : customConstructs.keySet()) {
            if (clazz.isAssignableFrom(type) || type.isAssignableFrom(clazz))
                throw new IncorrectImplementationException("an assignable type already exists");
        }
        customConstructs.put(type, constructor);
    }

    private void registerCustomIsConvertable(ICustomIsConvertable customIsConvertable) {
        if (customIsConvertables.contains(customIsConvertable))
            throw new IncorrectImplementationException("ICustomIsConvertable already exists");
        customIsConvertables.add(customIsConvertable);
    }

    private <T> void registerCustomConverter(Class<T> type, ICustomConverter<T> customConverter) {
        if (customConverters.containsKey(type))
            throw new IncorrectImplementationException(type + " already exists");
        for (var clazz : customConverters.keySet()) {
            if (clazz.isAssignableFrom(type) || type.isAssignableFrom(clazz))
                throw new IncorrectImplementationException("an assignable type already exists");
        }
        customConverters.put(type, customConverter);
    }

    public <T> void registerCustomType(ICustomTypeRegistry<T> customTypeRegistry) {
        registerCustomIsConvertable(customTypeRegistry);
        registerCustomConverter(customTypeRegistry.getRegisteringClass(), customTypeRegistry);
        registerCustomConstruct(customTypeRegistry.getRegisteringClass(), customTypeRegistry);
    }

    public boolean isCustomConvertable(Class<?> from, Class<?> to) {
        return customIsConvertables.stream().anyMatch(c -> c.isConvertable(from, to));
    }

    public Class<?> getClassByName(String name) {
        return types.getValue(name);
    }

    public String getClassName(Class<?> type) {
        return types.getKey(type);
    }

    public List<Class<?>> getClassesAssignableTo(Class<?>... typeArgs) {
        return Utils.streamToList(types.valueSet().stream()
                .filter(cls -> Arrays.stream(typeArgs).anyMatch(type -> type.isAssignableFrom(cls))));
    }

    public <T> ICustomConstruct<T> getCustomConstruct(Class<T> type) {
        for (var clazz : customConstructs.keySet()) {
            if (clazz.isAssignableFrom(type))
                return (ICustomConstruct<T>) customConstructs.get(clazz);
        }
        return null;
    }

    public <T> ICustomConverter<T> getCustomConverter(Class<T> type) {
        for (var clazz : customConverters.keySet()) {
            if (clazz.isAssignableFrom(type))
                return (ICustomConverter<T>) customConverters.get(clazz);
        }
        return null;
    }

    public interface ICustomConstruct<T> {
        T construct(Class<T> type, Map<String, Object> args);
    }

    public interface ICustomIsConvertable {
        boolean isConvertable(Class<?> from, Class<?> to);
    }

    public interface ICustomConverter<T> {
        void convert(Object from, Class<?> to, List<Conversion<?>> list, int depth);
    }

    public interface ICustomTypeRegistry<T> extends ICustomConstruct<T>, ICustomConverter<T>, ICustomIsConvertable {
        Class<T> getRegisteringClass();
    }
}
