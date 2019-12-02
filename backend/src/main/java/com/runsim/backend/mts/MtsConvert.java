package com.runsim.backend.mts;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MtsConvert {

    public static boolean isConvertable(Class<?> from, Class<?> to, boolean allowDeepConversion) {
        return isConvertable(from, to, new HashSet<>(), allowDeepConversion);
    }

    private static boolean isConvertable(Class<?> from, Class<?> to, HashSet<Class<?>> visitedSingleParams, boolean allowDeepConversion) {
        if (from.equals(to))
            return true;
        if (to.isAssignableFrom(from))
            return true;
        if (Traits.isNumberOrString(from) && Traits.isNumberOrString(to))
            return true;

        if (visitedSingleParams.contains(to))
            return true;

        visitedSingleParams.add(to);

        if (!allowDeepConversion)
            return false;

        for (var constructor : to.getDeclaredConstructors()) {
            if (constructor.getParameterCount() != 1)
                continue;

            if (isConvertable(from, constructor.getParameterTypes()[0], visitedSingleParams, allowDeepConversion)) {
                return true;
            }
        }

        return false;
    }

    private static <T> Conversion<T> numberConversion(Object value, Class<T> targetType, int depth) {
        var sourceType = value.getClass();

        if (!Traits.isNumberOrString(sourceType))
            throw new IllegalArgumentException();
        if (!Traits.isNumberOrString(targetType))
            throw new IllegalArgumentException();
        if (Traits.isSameNumberType(sourceType, targetType, true))
            return new Conversion<>(ConversionLevel.SAME_TYPE, (T) value, depth);
        if (Traits.isString(targetType))
            return new Conversion<>(ConversionLevel.NUMERIC_CONVERSION, (T) value.toString(), depth);
        if (Traits.isString(sourceType)) {
            Object parsed = Traits.parseNumber(targetType, (String) value);
            return new Conversion<>(ConversionLevel.NUMERIC_CONVERSION, (T) parsed, depth);
        }
        if (Traits.isFloatingPoint(sourceType) && Traits.isFloatingPoint(targetType)) {
            if (Traits.isFloat(sourceType)) {
                return new Conversion<>(ConversionLevel.ASSIGNABLE_TYPE, (T) Double.valueOf((Float) value), depth);
            } else {
                return new Conversion<>(ConversionLevel.NUMERIC_CONVERSION, (T) Float.valueOf((float) (double) (Double) value), depth);
            }
        }
        if (Traits.isFloatingPoint(targetType)) {
            long integral = Traits.getNumberAsLong(value);
            if (Traits.isFloat(sourceType)) {
                return new Conversion<>(ConversionLevel.ASSIGNABLE_TYPE, (T) Float.valueOf(integral), depth);
            } else {
                return new Conversion<>(ConversionLevel.ASSIGNABLE_TYPE, (T) Double.valueOf(integral), depth);
            }
        }
        if (Traits.isFloatingPoint(sourceType)) {
            if (Traits.isByte(targetType)) {
                return new Conversion<>(ConversionLevel.NUMERIC_CONVERSION, (T) (Byte) Traits.getNumberAsByte(value), depth);
            }
            if (Traits.isShort(targetType)) {
                return new Conversion<>(ConversionLevel.NUMERIC_CONVERSION, (T) (Short) Traits.getNumberAsShort(value), depth);
            }
            if (Traits.isInteger(targetType)) {
                return new Conversion<>(ConversionLevel.NUMERIC_CONVERSION, (T) (Integer) Traits.getNumberAsInt(value), depth);
            }
            if (Traits.isLong(targetType)) {
                return new Conversion<>(ConversionLevel.NUMERIC_CONVERSION, (T) (Long) Traits.getNumberAsLong(value), depth);
            }
            throw new RuntimeException();
        }

        if (Traits.isByte(targetType)) {
            return new Conversion<>(Traits.requiredConversionIntegral(sourceType, targetType), (T) (Byte) Traits.getNumberAsByte(value), depth);
        }
        if (Traits.isShort(targetType)) {
            return new Conversion<>(Traits.requiredConversionIntegral(sourceType, targetType), (T) (Short) Traits.getNumberAsShort(value), depth);
        }
        if (Traits.isInteger(targetType)) {
            return new Conversion<>(Traits.requiredConversionIntegral(sourceType, targetType), (T) (Integer) Traits.getNumberAsInt(value), depth);
        }
        if (Traits.isLong(targetType)) {
            return new Conversion<>(Traits.requiredConversionIntegral(sourceType, targetType), (T) (Long) Traits.getNumberAsLong(value), depth);
        }

        throw new IllegalArgumentException();
    }

    public static List<Conversion<?>> convert(Object from, Class<?> to, boolean allowDeepConversion) {
        List<Conversion<?>> list = new ArrayList<>();
        convert(from, to, list, new HashSet<>(), 0, allowDeepConversion);
        return list;
    }

    private static void convert(Object from, Class<?> to, List<Conversion<?>> list, HashSet<Class<?>> visitedSingleParams, int depth, boolean allowDeepConversion) {
        if (from == null) {
            list.add(new Conversion<>(ConversionLevel.LEVEL_NULL_CONVERSION, null, depth));
            return;
        }
        if (from.getClass().equals(to)) {
            list.add(new Conversion<>(ConversionLevel.SAME_TYPE, from, depth));
        } else if (to.isAssignableFrom(from.getClass())) {
            list.add(new Conversion<>(ConversionLevel.ASSIGNABLE_TYPE, from, depth));
        } else if (Traits.isNumberOrString(to) && Traits.isNumberOrString(from.getClass()) && Traits.isNumberIfString(from)) {
            list.add(numberConversion(from, to, depth));
        }

        if (visitedSingleParams.contains(to)) {
            return;
        }
        visitedSingleParams.add(to);

        if (!allowDeepConversion)
            return;

        for (var constructor : to.getDeclaredConstructors()) {
            if (constructor.getParameterCount() != 1)
                continue;
            if (!Modifier.isPublic(constructor.getModifiers()))
                continue;

            var ctorParamType = constructor.getParameterTypes()[0];
            if (isConvertable(from.getClass(), ctorParamType, new HashSet<>(), allowDeepConversion)) {

                var innerList = new ArrayList<Conversion<?>>();
                convert(from, ctorParamType, innerList, visitedSingleParams, depth + 1, allowDeepConversion);

                for (var inner : innerList) {
                    var val = inner.value;
                    try {
                        list.add(new Conversion<>(inner.level, constructor.newInstance(val), inner.depth));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
