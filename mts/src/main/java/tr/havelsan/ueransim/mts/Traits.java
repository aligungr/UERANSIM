/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.mts;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.function.Function;

public final class Traits {

    public static boolean isBoolean(Class<?> type) {
        return type == boolean.class || type == Boolean.class;
    }

    public static boolean isChar(Class<?> type) {
        return type == char.class || type == Character.class;
    }

    public static boolean isByte(Class<?> type) {
        return type == byte.class || type == Byte.class;
    }

    public static boolean isShort(Class<?> type) {
        return type == short.class || type == Short.class;
    }

    public static boolean isInteger(Class<?> type) {
        return type == int.class || type == Integer.class;
    }

    public static boolean isLong(Class<?> type) {
        return type == long.class || type == Long.class;
    }

    public static boolean isBigInteger(Class<?> type) {
        return type == BigInteger.class;
    }

    public static boolean isBigDecimal(Class<?> type) {
        return type == BigDecimal.class;
    }

    public static boolean isFloat(Class<?> type) {
        return type == float.class || type == Float.class;
    }

    public static boolean isDouble(Class<?> type) {
        return type == double.class || type == Double.class;
    }

    public static boolean isIntegral(Class<?> type) {
        return isByte(type) || isShort(type) || isInteger(type) || isLong(type);
    }

    public static boolean isFloatingPoint(Class<?> type) {
        return isFloat(type) || isDouble(type);
    }

    public static boolean isBig(Class<?> type) {
        return isBigInteger(type) || isBigDecimal(type);
    }

    public static boolean isNumber(Class<?> type) {
        return isIntegral(type) || isFloatingPoint(type) || isBig(type);
    }

    public static boolean isString(Class<?> type) {
        return type == String.class;
    }

    public static boolean isNumberOrString(Class<?> type) {
        return isNumber(type) || isString(type);
    }

    public static boolean isArray(Class<?> type) {
        return type.isArray();
    }

    public static boolean isCollection(Class<?> type) {
        return Collection.class.isAssignableFrom(type);
    }

    public static boolean isArrayOrCollection(Class<?> type) {
        return isArray(type) || isCollection(type);
    }

    public static long getNumberAsLong(Object value) {
        var type = value.getClass();

        if (isByte(type)) return (Byte) value;
        if (isShort(type)) return (Short) value;
        if (isInteger(type)) return ((Integer) value);
        if (isLong(type)) return (Long) value;
        if (isFloat(type)) return (long) (float) (Float) value;
        if (isDouble(type)) return (long) (double) (Double) value;

        throw new IllegalArgumentException();
    }

    public static byte getNumberAsByte(Object value) {
        return (byte) getNumberAsLong(value);
    }

    public static short getNumberAsShort(Object value) {
        return (short) getNumberAsLong(value);
    }

    public static int getNumberAsInt(Object value) {
        return (int) getNumberAsLong(value);
    }

    public static int integralSize(Class<?> type) {
        if (isByte(type)) return 1;
        if (isShort(type)) return 2;
        if (isInteger(type)) return 4;
        if (isLong(type)) return 8;
        throw new IllegalArgumentException();
    }

    public static Object parseNumber(Class<?> type, String string) {
        BigDecimal value;

        if (string.startsWith("0x") || string.startsWith("0X")) {
            value = new BigDecimal(new BigInteger(string.substring(2), 16));
        } else if (string.startsWith("0b") || string.startsWith("0B")) {
            value = new BigDecimal(new BigInteger(string.substring(2), 2));
        } else {
            value = new BigDecimal(string);
        }

        if (isByte(type)) return value.byteValue();
        if (isShort(type)) return value.shortValue();
        if (isInteger(type)) return value.intValue();
        if (isLong(type)) return value.longValue();
        if (isFloat(type)) return value.floatValue();
        if (isDouble(type)) return value.doubleValue();
        if (isBigInteger(type)) return value.toBigInteger();
        if (isBigDecimal(type)) return value;
        throw new IllegalArgumentException();
    }

    public static boolean isNumberIfString(Object object) {
        if (!(object instanceof String))
            return true;

        var str = (String) object;
        if (str.startsWith("0x") || str.startsWith("0X")) {
            try {
                new BigInteger(str.substring(2), 16);
            } catch (Exception ignored) {
                return false;
            }
            return true;
        }
        if (str.startsWith("0b") || str.startsWith("0B")) {
            try {
                new BigInteger(str.substring(2), 2);
            } catch (Exception ignored) {
                return false;
            }
            return true;
        }

        try {
            new BigDecimal(str);
        } catch (Exception ignored) {
            return false;
        }
        return true;
    }

    public static ConversionLevel requiredConversionIntegral(Class<?> from, Class<?> to) {
        int sizeA = integralSize(from);
        int sizeB = integralSize(to);
        if (sizeA == sizeB) return ConversionLevel.SAME_TYPE;
        if (sizeA > sizeB) return ConversionLevel.NUMERIC_CONVERSION;
        return ConversionLevel.ASSIGNABLE_TYPE;
    }

    public static boolean isSameNumberType(Class<?> sourceType, Class<?> targetType) {
        if (!isNumberOrString(sourceType) || !isNumberOrString(targetType))
            throw new IllegalArgumentException();

        Function<Class<?>, Class<?>> getWrapperType = (Class<?> type) -> {
            if (isByte(type)) return Byte.class;
            if (isShort(type)) return Short.class;
            if (isInteger(type)) return Integer.class;
            if (isLong(type)) return Long.class;
            if (isFloat(type)) return Float.class;
            if (isDouble(type)) return Double.class;
            return type;
        };

        return getWrapperType.apply(sourceType).equals(getWrapperType.apply(targetType));
    }

    public static boolean isNullable(Class<?> type) {
        return !(type == boolean.class || type == char.class || type == byte.class || type == short.class
                || type == int.class || type == long.class || type == float.class || type == double.class);
    }

    public static Class<?> wrapperOfPrimitive(Class<?> type) {
        if (type == boolean.class) return Boolean.class;
        if (type == char.class) return Character.class;
        if (type == byte.class) return Byte.class;
        if (type == short.class) return Short.class;
        if (type == int.class) return Integer.class;
        if (type == long.class) return Long.class;
        if (type == float.class) return Float.class;
        if (type == double.class) return Double.class;
        if (type == void.class) return Void.class;
        return type;
    }

    public static Class<?> primitiveOfWrapper(Class<?> type) {
        if (type == Boolean.class) return boolean.class;
        if (type == Character.class) return char.class;
        if (type == Byte.class) return byte.class;
        if (type == Short.class) return short.class;
        if (type == Integer.class) return int.class;
        if (type == Long.class) return long.class;
        if (type == Float.class) return float.class;
        if (type == Double.class) return double.class;
        if (type == Void.class) return void.class;
        return type;
    }
}
