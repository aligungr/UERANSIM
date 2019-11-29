package com.runsim.backend.mts;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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

    public static boolean convertable(Class<?> from, Class<?> to) {
        return convertable(from, to, new HashSet<>());
    }

    private static boolean convertable(Class<?> from, Class<?> to, HashSet<Class<?>> visitedSingleParams) {
        if (from.equals(to))
            return true;
        if (to.isAssignableFrom(from))
            return true;
        if (Traits.isNumberOrString(from) && Traits.isNumberOrString(to))
            return true;

        if (visitedSingleParams.contains(to))
            return true;

        visitedSingleParams.add(to);

        for (var constructor : to.getDeclaredConstructors()) {
            if (constructor.getParameterCount() != 1)
                continue;

            if (convertable(from, constructor.getParameterTypes()[0], visitedSingleParams)) {
                return true;
            }
        }

        return false;
    }

    private static <T> Conversion<T> numberConversion(Object value, Class<T> targetType) {
        var sourceType = value.getClass();

        if (!Traits.isNumberOrString(sourceType))
            throw new IllegalArgumentException();
        if (!Traits.isNumberOrString(targetType))
            throw new IllegalArgumentException();
        if (sourceType == targetType)
            return new Conversion<>(ConversionLevel.SAME_TYPE, (T) value);
        if (Traits.isString(targetType))
            return new Conversion<>(ConversionLevel.NUMERIC_CONVERSION, (T) value.toString());
        if (Traits.isString(sourceType)) {
            Object parsed = Traits.parseNumber(targetType, (String) value);
            return new Conversion<>(ConversionLevel.NUMERIC_CONVERSION, (T) parsed);
        }
        if (Traits.isFloatingPoint(sourceType) && Traits.isFloatingPoint(targetType)) {
            if (Traits.isFloat(sourceType)) {
                return new Conversion<>(ConversionLevel.ASSIGNABLE_TYPE, (T) Double.valueOf((Float) value));
            } else {
                return new Conversion<>(ConversionLevel.NUMERIC_CONVERSION, (T) Float.valueOf((float) (double) (Double) value));
            }
        }
        if (Traits.isFloatingPoint(targetType)) {
            long integral = Traits.getNumberAsLong(value);
            if (Traits.isFloat(sourceType)) {
                return new Conversion<>(ConversionLevel.ASSIGNABLE_TYPE, (T) Float.valueOf(integral));
            } else {
                return new Conversion<>(ConversionLevel.ASSIGNABLE_TYPE, (T) Double.valueOf(integral));
            }
        }
        if (Traits.isFloatingPoint(sourceType)) {
            if (Traits.isByte(targetType)) {
                return new Conversion<>(ConversionLevel.NUMERIC_CONVERSION, (T) (Byte) Traits.getNumberAsByte(value));
            }
            if (Traits.isShort(targetType)) {
                return new Conversion<>(ConversionLevel.NUMERIC_CONVERSION, (T) (Short) Traits.getNumberAsShort(value));
            }
            if (Traits.isInteger(targetType)) {
                return new Conversion<>(ConversionLevel.NUMERIC_CONVERSION, (T) (Integer) Traits.getNumberAsInt(value));
            }
            if (Traits.isLong(targetType)) {
                return new Conversion<>(ConversionLevel.NUMERIC_CONVERSION, (T) (Long) Traits.getNumberAsLong(value));
            }
            throw new RuntimeException();
        }

        if (Traits.isByte(targetType)) {
            return new Conversion<>(Traits.requiredConversionIntegral(sourceType, targetType), (T) (Byte) Traits.getNumberAsByte(value));
        }
        if (Traits.isShort(targetType)) {
            return new Conversion<>(Traits.requiredConversionIntegral(sourceType, targetType), (T) (Short) Traits.getNumberAsShort(value));
        }
        if (Traits.isInteger(targetType)) {
            return new Conversion<>(Traits.requiredConversionIntegral(sourceType, targetType), (T) (Integer) Traits.getNumberAsInt(value));
        }
        if (Traits.isLong(targetType)) {
            return new Conversion<>(Traits.requiredConversionIntegral(sourceType, targetType), (T) (Long) Traits.getNumberAsLong(value));
        }

        throw new IllegalArgumentException();
    }

    public static List<Conversion<?>> convert(Object from, Class<?> to) {
        var list = new ArrayList<Conversion<?>>();
        convert(from, to, list, new HashSet<>(), new AtomicInteger(0));
        return list;
    }

    private static void convert(Object from, Class<?> to, ArrayList<Conversion<?>> list, HashSet<Class<?>> visitedSingleParams, AtomicInteger currentDepth) {
        if (from == null) {
            list.add(new Conversion<>(ConversionLevel.LEVEL_NULL_CONVERSION, null));
            return;
        }
        if (from.getClass().equals(to)) {
            list.add(new Conversion<>(ConversionLevel.SAME_TYPE, from));
        } else if (to.isAssignableFrom(from.getClass())) {
            list.add(new Conversion<>(ConversionLevel.ASSIGNABLE_TYPE, from));
        } else if (Traits.isNumberOrString(from.getClass()) && Traits.isNumberOrString(to)) {
            list.add(numberConversion(from, to));
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
            if (convertable(from.getClass(), ctorParamType, new HashSet<>())) {

                var innerList = new ArrayList<Conversion<?>>();
                convert(from, ctorParamType, innerList, visitedSingleParams, currentDepth);

                for (var inner : innerList) {
                    var val = inner.value;
                    try {
                        list.add(new Conversion<>(currentDepth.get() == 1 ? inner.level : ConversionLevel.LEVEL_DEEP_CONVERT, constructor.newInstance(val)));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        currentDepth.incrementAndGet();
    }

    public static boolean canBeConstructed(Class<?> a, Class<?> b) {
        return true;
    }
}
