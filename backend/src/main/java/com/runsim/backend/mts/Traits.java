package com.runsim.backend.mts;

import java.math.BigDecimal;

public final class Traits {

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

    public static boolean isNumber(Class<?> type) {
        return isIntegral(type) || isFloatingPoint(type);
    }

    public static boolean isString(Class<?> type) {
        return type == String.class;
    }

    public static boolean isNumberOrString(Class<?> type) {
        return isNumber(type) || isString(type);
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
        var value = new BigDecimal(string);
        if (isByte(type)) return value.byteValue();
        if (isShort(type)) return value.shortValue();
        if (isInteger(type)) return value.intValue();
        if (isLong(type)) return value.longValue();
        if (isFloat(type)) return value.floatValue();
        if (isDouble(type)) return value.doubleValue();
        throw new IllegalArgumentException();
    }

    public static boolean isNumberIfString(Object object) {
        if (!(object instanceof String))
            return true;
        try {
            new BigDecimal((String) object);
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
}
