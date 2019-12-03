package com.runsim.backend.mts;

import com.runsim.backend.exceptions.IncorrectImplementationException;
import com.runsim.backend.utils.BiMap;

import java.util.*;

public final class TypeRegistry {
    static final boolean ALLOW_DEEP_CONVERSION = false;

    private static final BiMap<String, Class<?>> types;
    private static final Map<Class<?>, ICustomConstruct<?>> customConstructs;
    private static final Set<ICustomIsConvertable> customIsConvertables;
    private static final Map<Class<?>, ICustomConverter<?>> customConverters;

    static {
        types = new BiMap<>();
        customConstructs = new HashMap<>();
        customIsConvertables = new HashSet<>();
        customConverters = new HashMap<>();
    }

    public static void registerTypeName(String name, Class<?> type) {
        if (types.containsKey(name))
            throw new IncorrectImplementationException(name + " already exists");
        if (types.containsValue(type))
            throw new IncorrectImplementationException(type + " already exists");
        types.put(name, type);
    }

    private static <T> void registerCustomConstruct(Class<T> type, ICustomConstruct<T> constructor) {
        if (customConstructs.containsKey(type))
            throw new IncorrectImplementationException(type + " already exists");
        for (var clazz : customConstructs.keySet()) {
            if (clazz.isAssignableFrom(type) || type.isAssignableFrom(clazz))
                throw new IncorrectImplementationException("an assignable type already exists");
        }
        customConstructs.put(type, constructor);
    }

    private static void registerCustomIsConvertable(ICustomIsConvertable customIsConvertable) {
        if (customIsConvertables.contains(customIsConvertable))
            throw new IncorrectImplementationException("ICustomIsConvertable already exists");
        customIsConvertables.add(customIsConvertable);
    }

    private static <T> void registerCustomConverter(Class<T> type, ICustomConverter<T> customConverter) {
        if (customConverters.containsKey(type))
            throw new IncorrectImplementationException(type + " already exists");
        for (var clazz : customConverters.keySet()) {
            if (clazz.isAssignableFrom(type) || type.isAssignableFrom(clazz))
                throw new IncorrectImplementationException("an assignable type already exists");
        }
        customConverters.put(type, customConverter);
    }

    public static <T> void registerCustomType(ICustomTypeRegistry<T> customTypeRegistry) {
        registerCustomIsConvertable(customTypeRegistry);
        registerCustomConverter(customTypeRegistry.getRegisteringClass(), customTypeRegistry);
        registerCustomConstruct(customTypeRegistry.getRegisteringClass(), customTypeRegistry);
    }

    public static boolean isCustomConvertable(Class<?> from, Class<?> to) {
        return customIsConvertables.stream().anyMatch(c -> c.isConvertable(from, to));
    }

    public static Class<?> getClassByName(String name) {
        return types.getValue(name);
    }

    public static String getClassName(Class<?> type) {
        return types.getKey(type);
    }

    public static <T> ICustomConstruct<T> getCustomConstruct(Class<T> type) {
        for (var clazz : customConstructs.keySet()) {
            if (clazz.isAssignableFrom(type))
                return (ICustomConstruct<T>) customConstructs.get(clazz);
        }
        return null;
    }

    public static <T> ICustomConverter<T> getCustomConverter(Class<T> type) {
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
