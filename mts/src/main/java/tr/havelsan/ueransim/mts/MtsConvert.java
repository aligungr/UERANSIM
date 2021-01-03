/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.mts;

import tr.havelsan.ueransim.utils.Utils;

import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MtsConvert {

    private final MtsContext ctx;

    MtsConvert(MtsContext ctx) {
        this.ctx = ctx;
    }

    public boolean isConvertable(Class<?> from, Class<?> to, boolean includeCustoms) {
        return isConvertable(from, to, new HashSet<>(), includeCustoms);
    }

    private boolean isConvertable(Class<?> from, Class<?> to, HashSet<Class<?>> visitedSingleParams, boolean includeCustoms) {
        if (from.equals(to))
            return true;
        if (to.isAssignableFrom(from))
            return true;
        if (Traits.isNumberOrString(from) && Traits.isNumberOrString(to))
            return true;
        if (Traits.isBoolean(from) && Traits.isBoolean(to))
            return true;
        if (Traits.isChar(from) && Traits.isChar(to))
            return true;
        if (Traits.isCollection(from) || Traits.isCollection(to)) {
            throw new MtsException("collections are not supported, use array instead");
        } else if (Traits.isArray(from) || Traits.isArray(to)) {
            return Traits.isArray(from) && Traits.isArray(to);
        }
        if (includeCustoms) {
            if (ctx.typeRegistry.isCustomConvertable(from, to))
                return true;
        }

        if (visitedSingleParams.contains(to))
            return false;

        visitedSingleParams.add(to);

        if (!TypeRegistry.ALLOW_DEEP_CONVERSION)
            return false;

        for (var constructor : to.getDeclaredConstructors()) {
            if (constructor.getParameterCount() != 1)
                continue;

            if (isConvertable(from, constructor.getParameterTypes()[0], visitedSingleParams, includeCustoms)) {
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

        if (Traits.isSameNumberType(sourceType, targetType))
            return new Conversion<>(ConversionLevel.SAME_TYPE, (T) value, depth);

        if (Traits.isString(targetType))
            return new Conversion<>(ConversionLevel.NUMERIC_CONVERSION, (T) value.toString(), depth);
        if (Traits.isString(sourceType)) {
            Object parsed = Traits.parseNumber(targetType, (String) value);
            return new Conversion<>(ConversionLevel.NUMERIC_CONVERSION, (T) parsed, depth);
        }

        if (Traits.isBig(targetType)) {
            var numberInfo = new NumberInfo((Number) value);
            if (Traits.isBigDecimal(targetType)) {
                return new Conversion<>(ConversionLevel.ASSIGNABLE_TYPE, (T) numberInfo.bigDecimalValue(), depth);
            } else {
                return new Conversion<>(numberInfo.isFractionalNumber() ? ConversionLevel.NUMERIC_CONVERSION : ConversionLevel.ASSIGNABLE_TYPE, (T) numberInfo.bigDecimalValue(), depth);
            }
        }
        if (Traits.isBig(sourceType)) {
            Object parsed = Traits.parseNumber(targetType, value.toString());
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

    /**
     * Returns conversion result as list as sorted. (The less index means more preferable conversion)
     */
    public List<Conversion<?>> convert(Object from, Class<?> to, boolean includeCustoms) {
        List<Conversion<?>> list = new ArrayList<>();
        convert(from, to, list, new HashSet<>(), 0, includeCustoms);
        list.sort(Comparator.comparingInt(a -> a.level.getLevel()));
        return list;
    }

    private void convert(Object from, Class<?> to, List<Conversion<?>> list, HashSet<Class<?>> visitedSingleParams, int depth, boolean includeCustoms) {
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
        } else if (Traits.isBoolean(from.getClass()) && Traits.isBoolean(to)) {
            list.add(new Conversion<>(ConversionLevel.SAME_TYPE, (Boolean) (boolean) from, depth));
        } else if (Traits.isChar(from.getClass()) && Traits.isChar(to)) {
            list.add(new Conversion<>(ConversionLevel.SAME_TYPE, (Character) (char) from, depth));
        } else if (Traits.isCollection(from.getClass()) || Traits.isCollection(to)) {
            throw new MtsException("collections are not supported, use array instead");
        } else if (Traits.isArray(from.getClass()) || Traits.isArray(to)) {
            if (Traits.isArray(from.getClass()) && Traits.isArray(to)) {
                convertArray(from, to, list, depth, includeCustoms);
            }
            return;
        } else if (includeCustoms) {
            var customConverter = ctx.typeRegistry.getCustomConverter(to);
            if (customConverter != null) {
                customConverter.convert(from, to, list, depth);
                return;
            }
        }

        if (visitedSingleParams.contains(to)) {
            return;
        }
        visitedSingleParams.add(to);

        if (!TypeRegistry.ALLOW_DEEP_CONVERSION)
            return;

        for (var constructor : to.getDeclaredConstructors()) {
            if (constructor.getParameterCount() != 1)
                continue;
            if (!Modifier.isPublic(constructor.getModifiers()))
                continue;

            var ctorParamType = constructor.getParameterTypes()[0];
            if (isConvertable(from.getClass(), ctorParamType, new HashSet<>(), includeCustoms)) {

                var innerList = new ArrayList<Conversion<?>>();
                convert(from, ctorParamType, innerList, visitedSingleParams, depth + 1, includeCustoms);

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

    private void convertArray(Object from, Class<?> to, List<Conversion<?>> list, int depth, boolean includeCustoms) {
        Class<?> elementType = to.getComponentType();

        // This function converts primitive arrays to wrapper arrays.
        // Example: int[] -> Integer[]
        Function<Object, Object[]> eliminatePrimitiveArray = arr -> {
            var arrayList = new ArrayList<>();
            for (int i = 0; i < Array.getLength(arr); i++)
                arrayList.add(Array.get(arr, i));
            return arrayList.toArray();
        };

        // This function converts primitive arrays to wrapper arrays.
        // Example: Integer[] -> int[]
        BiFunction<Object[], Class<?>, Object> makePrimitiveArray = (arr, primitiveType) -> {
            var primitiveArray = Array.newInstance(primitiveType, arr.length);
            for (int i = 0; i < arr.length; i++)
                Array.set(primitiveArray, i, arr[i]);
            return primitiveArray;
        };

        Object[] sourceArray = eliminatePrimitiveArray.apply(from);
        Class<?> targetType = Traits.wrapperOfPrimitive(elementType);

        for (int i = 0; i < sourceArray.length; i++) {
            if (sourceArray[i] instanceof ImplicitTypedObject) {
                sourceArray[i] = ctx.constructor.construct(targetType, (ImplicitTypedObject) sourceArray[i], includeCustoms);
            }

            var conversions = convert(sourceArray[i], targetType, includeCustoms);

            var shallowConversions = Utils.streamToList(conversions.stream().filter(conversion -> conversion.depth == 0));
            var deepConversions = Utils.streamToList(conversions.stream().filter(conversion -> conversion.depth != 0));

            if (shallowConversions.size() == 0 && deepConversions.size() == 0) {
                throw new MtsException("element at index '%d' has type '%s', but expected type is '%s' or convertable types.", i, sourceArray[i].getClass().getSimpleName(), targetType.getSimpleName());
            }

            Conversion<?> selectedConversion;

            if (shallowConversions.size() == 0) {
                if (deepConversions.size() > 1) {
                    throw new MtsException("multiple convertions matched for element at index '%d'", i);
                }
                selectedConversion = deepConversions.get(0);
            } else if (shallowConversions.size() > 1) {
                throw new MtsException("multiple convertions matched for element at index '%d'", i);
            } else {
                selectedConversion = shallowConversions.get(0);
            }

            sourceArray[i] = selectedConversion.value;
        }

        if (elementType.isPrimitive()) {
            from = makePrimitiveArray.apply(sourceArray, elementType);
        } else {
            Object created = Array.newInstance(elementType, sourceArray.length);
            for (int i = 0; i < sourceArray.length; i++) {
                Array.set(created, i, sourceArray[i]);
            }

            from = created;
        }
        // (arrays are assumed as assignable_type, and it not important)
        list.add(new Conversion<>(ConversionLevel.ASSIGNABLE_TYPE, from, depth));
    }
}
