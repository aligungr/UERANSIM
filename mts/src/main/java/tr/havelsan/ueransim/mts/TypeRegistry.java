/*
 * MIT License
 *
 * Copyright (c) 2020 ALİ GÜNGÖR
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * @author Ali Güngör (aligng1620@gmail.com)
 */

package tr.havelsan.ueransim.mts;

import tr.havelsan.ueransim.core.exceptions.IncorrectImplementationException;
import tr.havelsan.ueransim.utils.BiMap;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;

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

    public static List<Class<?>> getClassesAssignableTo(Class<?>... typeArgs) {
        return Utils.streamToList(types.valueSet().stream()
                .filter(cls -> Arrays.stream(typeArgs).anyMatch(type -> type.isAssignableFrom(cls))));
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
