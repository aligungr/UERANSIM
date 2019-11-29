package com.runsim.backend.mts;

import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class MtsInfo {

    public static boolean allAssignableFrom(Class<?>[] lhs, Class<?>[] rhs) {
        if (lhs.length != rhs.length)
            return false;
        for (int i = 0; i < lhs.length; i++) {
            if (!lhs[i].isAssignableFrom(rhs[i]))
                return false;
        }
        return true;
    }

    public static boolean constructableFromParameters(Class<?> type, Class<?>... params) {
        return Arrays.stream(type.getDeclaredConstructors())
                .filter(constructor -> constructor.getParameterCount() == params.length)
                .anyMatch(constructor -> allAssignableFrom(constructor.getParameterTypes(), params));
    }

    public static ConversionLevel convertable(Class<?> from, Class<?> to) {
        return convertable(from, to, new HashSet<>());
    }

    private static ConversionLevel convertable(Class<?> from, Class<?> to, HashSet<Class<?>> visitedSingleParams) {
        if (from.equals(to))
            return ConversionLevel.LEVEL_EXACT_MATCH;
        if (to.isAssignableFrom(from))
            return ConversionLevel.LEVEL_ASSIGNABLE;
        if (canBeNumber(from) && canBeNumber(to))
            return ConversionLevel.LEVEL_NUMBER_CONVERT;

        if (visitedSingleParams.contains(to))
            return ConversionLevel.LEVEL_NO_CONVERSION;

        visitedSingleParams.add(to);

        for (var constructor : to.getDeclaredConstructors()) {
            if (constructor.getParameterCount() != 1)
                continue;

            if (convertable(from, constructor.getParameterTypes()[0], visitedSingleParams) != ConversionLevel.LEVEL_NO_CONVERSION) {
                return ConversionLevel.LEVEL_DEEP_CONVERT;
            }
        }

        return ConversionLevel.LEVEL_NO_CONVERSION;
    }

    public static List<Conversion<?>> convert(Object from, Class<?> to) {
        var list = new ArrayList<Conversion<?>>();
        convert(from, to, list, new HashSet<>(), false);


        return list;
    }

    private static void convert(Object from, Class<?> to, ArrayList<Conversion<?>> list, HashSet<Class<?>> visitedSingleParams, boolean currentlyDeep) {
        if (from == null) {
            list.add(new Conversion<>(ConversionLevel.LEVEL_NULL_CONVERSION, null));
            return;
        }
        if (from.getClass().equals(to)) {
            list.add(new Conversion<>(ConversionLevel.LEVEL_EXACT_MATCH, from));
        } else if (to.isAssignableFrom(from.getClass())) {
            list.add(new Conversion<>(ConversionLevel.LEVEL_ASSIGNABLE, from));
        } else if (canBeNumber(from.getClass()) && canBeNumber(to)) {
            list.add(new Conversion<>(ConversionLevel.LEVEL_NUMBER_CONVERT, convertToNumber(from, to)));
        }

        if (visitedSingleParams.contains(to)) {
            return;
        }
        visitedSingleParams.add(to);

        for (var constructor : to.getDeclaredConstructors()) {
            if (constructor.getParameterCount() != 1)
                continue;
            if (!Modifier.isPublic(constructor.getModifiers()))
                continue;

            var ctorParamType = constructor.getParameterTypes()[0];
            if (convertable(from.getClass(), ctorParamType, visitedSingleParams) != ConversionLevel.LEVEL_NO_CONVERSION) {

                var innerList = new ArrayList<Conversion<?>>();
                convert(from, ctorParamType, innerList, visitedSingleParams, true);

                for (var inner : innerList) {
                    var val = inner.value;
                    try {
                        list.add(new Conversion<>(inner.level, constructor.newInstance(val)));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    private static boolean canBeNumber(Class<?> type) {
        final var types = new Class[]{
                byte.class, short.class, int.class, long.class,
                float.class, double.class,
                Byte.class, Short.class, Integer.class, Long.class,
                Float.class, Double.class,

                String.class,
                BigInteger.class, BigDecimal.class
        };
        for (var t : types) {
            if (t.equals(type))
                return true;
        }
        return false;
    }

    private static <T> T convertToNumber(Object obj, Class<T> type) {
        String s = obj.toString();
        if (type == String.class)
            return (T) s;
        if (type == BigInteger.class)
            return (T) new BigInteger(s);
        if (type == BigDecimal.class)
            return (T) new BigDecimal(s);
        if (type == byte.class || type == Byte.class)
            return (T) Byte.valueOf(Byte.parseByte(s));
        if (type == short.class || type == Short.class)
            return (T) Short.valueOf(Short.parseShort(s));
        if (type == int.class || type == Integer.class)
            return (T) Integer.valueOf(Integer.parseInt(s));
        if (type == long.class || type == Long.class)
            return (T) Long.valueOf(Long.parseLong(s));
        if (type == float.class || type == Float.class)
            return (T) Float.valueOf(Float.parseFloat(s));
        if (type == double.class || type == Double.class)
            return (T) Double.valueOf(Double.parseDouble(s));
        throw new RuntimeException();
    }

    public static boolean canBeConstructed(Class<?> a, Class<?> b) {
        return true;
    }
}
