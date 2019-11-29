package com.runsim.backend.mts;

import com.runsim.backend.utils.Color;
import com.runsim.backend.utils.Console;
import com.runsim.backend.utils.Utils;
import com.runsim.backend.utils.octets.Octet2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Objects;

public class MtsTest {

    static final Object CANNOT = new Object();

    private static <T> T newInstance(Constructor<T> ctor, Object... params) {
        try {
            return ctor.newInstance(params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> T constructType(Constructor<T> ctor, Object... params) {
        var ctorParamTypes = ctor.getParameterTypes();
        var actualVals = new Object[params.length];

        for (int i = 0; i < params.length; i++) {
            var ctorParamType = ctorParamTypes[i];
            var param = params[i];

            var value = constructType(ctorParamType, param);
            if (value == null)
                return null;
            actualVals[i] = value;
        }

        return newInstance(ctor, actualVals);
    }

    static <T> T constructType(Class<T> type, Object... params) {
        // TODO: paramlar null içeremesin

        if (type.equals(int.class) || type.equals(Integer.class)) {
            if (params.length != 1) return null;
            if (params[0] instanceof Integer) {
                int integer = (Integer) params[0];
                return (T) Integer.valueOf(integer);
            }
            return null;
        }

        if (type.equals(String.class)) {
            if (params.length != 1) return null;
            if (params[0] instanceof String) {
                return (T) String.valueOf(params[0]);
            }
            return null;
        }

        var array = Arrays.stream(type.getDeclaredConstructors())
                .filter(constructor -> constructor.getParameterCount() == params.length)
                .map(constructor -> constructType(constructor, params))
                .filter(Objects::nonNull).toArray();

        if (array.length == 0)
            return null;
        if (array.length != 1)
            throw new RuntimeException("multiple matching found");
        return (T) array[0];
    }

    static Object createInstanceForTypeWithValue(Class<?> type, Object value) {
        if (value == null)
            return null;
        if (type.isAssignableFrom(value.getClass())) {
            return value;
        }
        return CANNOT;
    }

    static void createInstanceForTypeWithDescriptor(Class<?> type, ValueDescriptor descriptor) {
        String descriptorTypeName = descriptor.getTypeName();
        if (descriptorTypeName == null) {
            var typeName = TypeRegistry.getClassName(type);
            if (typeName == null) {
                throw new MtsException("descriptor has no explicit type name and required type '%s' is not registered", type.getSimpleName());
            }
            descriptorTypeName = typeName;
        }

        Class<?> instanceType = TypeRegistry.getClassByName(descriptorTypeName);
        if (instanceType == null) {
            throw new MtsException("type is not registered with name '%s'", descriptorTypeName);
        }

        if (!type.isAssignableFrom(instanceType)) {
            throw new MtsException("type '%s' is not assignable from type '%s'", type.getSimpleName(), instanceType.getSimpleName());
        }

        var constructors = Utils.streamToList(Arrays.stream(type.getDeclaredConstructors())
                .filter(constructor -> constructor.getParameterCount() == descriptor.getParameterCount())
                .filter(constructor -> Arrays.equals(descriptor.getParameterNames(), Arrays.stream(constructor.getParameters())
                        .map(Parameter::getName)
                        .toArray())));

        if (constructors.size() == 0) {
            throw new MtsException("no matching constructor found for type '%s' with given parameters", instanceType.getSimpleName());
        }
    }

    public static void main(String[] args) throws Exception {
        MtsInfo.convert(
                5,
                Octet2.class
        ).forEach(conversion -> {
            Console.println(Color.RED, conversion.level);
            Console.println(Color.BLUE, conversion.value);
        });

        Console.println();
    }
}
