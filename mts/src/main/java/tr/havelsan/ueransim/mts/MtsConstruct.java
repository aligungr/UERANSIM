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

import tr.havelsan.ueransim.utils.Utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

public class MtsConstruct {

    private static boolean parameterCountMatches(Constructor<?> constructor, Map<String, Object> parameters) {
        return constructor.getParameterCount() >= parameters.size();
    }

    private static boolean parameterNameMatches(Constructor<?> constructor, Map<String, Object> parameters) {
        if (constructor.getParameterCount() < parameters.size())
            return false;

        var ctorParams = new HashSet<String>();
        for (var param : constructor.getParameters()) {
            ctorParams.add(param.getName());
        }

        for (var s : parameters.keySet()) {
            if (!ctorParams.contains(s))
                return false;
        }
        return true;
    }

    private static boolean parameterTypeMatches(Constructor<?> constructor, Map<String, Object> parameters, boolean includeCustoms) {
        if (constructor.getParameterCount() < parameters.size())
            return false;
        for (var param : constructor.getParameters()) {
            var object = parameters.get(param.getName());
            if (object instanceof ImplicitTypedObject) {
                if (Traits.isNumberOrString(param.getType()))
                    return false;
                continue;
            }
            if (object == null) {
                if (Traits.isNullable(param.getType())) {
                    continue;
                } else {
                    return false;
                }
            }
            if (!MtsConvert.isConvertable(object.getClass(), param.getType(), includeCustoms))
                return false;
        }
        return true;
    }

    private static boolean parameterTypeExactMatches(Constructor<?> constructor, Map<String, Object> parameters, boolean includeCustoms) {
        if (constructor.getParameterCount() != parameters.size())
            return false;
        for (var param : constructor.getParameters()) {
            var object = parameters.get(param.getName());
            var conversion = MtsConvert.convert(object, param.getType(), includeCustoms);
            if (conversion.stream().noneMatch(c -> c.depth == 0 && (c.level == ConversionLevel.SAME_TYPE))) {
                return false;
            }
        }
        return true;
    }

    public static <T> T construct(Class<T> type, Map<String, Object> args, boolean includeCustoms) {
        if (includeCustoms) {
            var customConstruct = TypeRegistry.getCustomConstruct(type);
            if (customConstruct != null)
                return customConstruct.construct(type, args);
        }

        var constructors = Arrays.asList(type.getDeclaredConstructors());

        var matched = Utils.streamToList(constructors.stream().filter(constructor -> Modifier.isPublic(constructor.getModifiers())));
        matched = Utils.streamToList(matched.stream().filter(constructor -> parameterCountMatches(constructor, args)));

        if (matched.size() == 0) {
            throw new MtsException("no constructor found for %d parameters for type %s", args.size(), type.getSimpleName());
        }

        matched = Utils.streamToList(matched.stream().filter(constructor -> parameterNameMatches(constructor, args)));
        if (matched.size() == 0) {
            throw new MtsException("no constructor found for given parameter names for type %s", type.getSimpleName());
        }

        matched = Utils.streamToList(matched.stream().filter(constructor -> parameterTypeMatches(constructor, args, includeCustoms)));
        if (matched.size() == 0) {
            throw new MtsException("no constructor found for given parameter values for type %s", type.getSimpleName());
        }
        if (matched.size() > 1) {
            var exactMatched = Utils.streamToList(matched.stream().filter(constructor -> parameterTypeExactMatches(constructor, args, includeCustoms)));
            if (exactMatched.size() != 1) {
                throw new MtsException("multiple constructor found for given parameter values for type %s", type.getSimpleName());
            } else {
                matched = exactMatched;
            }
        }

        var constructor = matched.get(0);

        var params = constructor.getParameters();
        var paramInstances = new Object[params.length];

        for (int j = 0; j < params.length; j++) {
            var param = params[j];
            var value = args.get(param.getName());

            if (value instanceof ImplicitTypedObject) {
                value = construct(param.getType(), (ImplicitTypedObject) value, includeCustoms);
            }

            var conversions = MtsConvert.convert(value, param.getType(), includeCustoms);

            var shallowConversions = Utils.streamToList(conversions.stream().filter(conversion -> conversion.depth == 0));
            var deepConversions = Utils.streamToList(conversions.stream().filter(conversion -> conversion.depth != 0));

            if (shallowConversions.size() == 0 && deepConversions.size() == 0) {
                throw new MtsException("'%s' parameter value has type '%s', but expected type is '%s' or convertable types.", param.getName(), value.getClass().getSimpleName(), param.getType().getSimpleName());
            }

            Conversion<?> selectedConversion;

            if (shallowConversions.size() == 0) {
                if (deepConversions.size() > 1) {
                    throw new MtsException("multiple convertions matched for parameter '%s'", param.getName());
                }
                selectedConversion = deepConversions.get(0);
            } else if (shallowConversions.size() > 1) {
                throw new MtsException("multiple convertions matched for parameter '%s'", param.getName());
            } else {
                selectedConversion = shallowConversions.get(0);
            }

            paramInstances[j] = selectedConversion.value;
        }

        try {
            return (T) constructor.newInstance(paramInstances);
        } catch (InvocationTargetException e) {
            throw new MtsException("construction failed for type %s, error message: %s", type.getSimpleName(), e.getTargetException().getMessage());
        } catch (Exception e) {
            throw new MtsException("Instantiation failed");
        }
    }

    public static <T> T construct(Class<T> type, ImplicitTypedObject implicitTypedObject, boolean includeCustoms) {
        return construct(type, implicitTypedObject.getParameters(), includeCustoms);
    }
}
