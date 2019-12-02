package com.runsim.backend.mts;

import com.runsim.backend.exceptions.MtsException;
import com.runsim.backend.nas.core.ProtocolEnum;
import com.runsim.backend.utils.Utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

public class MtsConstruct {
    private final MtsConvert mtsConvert;

    public MtsConstruct(boolean allowDeepConversion) {
        this.mtsConvert = new MtsConvert(allowDeepConversion);
    }

    private boolean parameterCountMatches(Constructor<?> constructor, Map<String, Object> parameters) {
        return constructor.getParameterCount() >= parameters.size();
    }

    private boolean parameterNameMatches(Constructor<?> constructor, Map<String, Object> parameters) {
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

    private boolean parameterTypeMatches(Constructor<?> constructor, Map<String, Object> parameters) {
        if (constructor.getParameterCount() < parameters.size())
            return false;
        for (var param : constructor.getParameters()) {
            var object = parameters.get(param.getName());
            if (object instanceof ImplicitTypedValue) {
                continue;
            }
            if (object == null) {
                if (Traits.isNullable(param.getType())) {
                    continue;
                } else {
                    return false;
                }
            }
            if (!mtsConvert.isConvertable(object.getClass(), param.getType()))
                return false;
        }
        return true;
    }

    private boolean parameterTypeExactMatches(Constructor<?> constructor, Map<String, Object> parameters) {
        if (constructor.getParameterCount() != parameters.size())
            return false;
        for (var param : constructor.getParameters()) {
            var object = parameters.get(param.getName());
            var conversion = mtsConvert.convert(object, param.getType());
            if (conversion.stream().noneMatch(c -> c.depth == 0 && (c.level == ConversionLevel.SAME_TYPE))) {
                return false;
            }
        }
        return true;
    }

    public <T> T construct(Class<T> type, Map<String, Object> args) {
        // TODO: buun gibi custom olanları cohernece yap.
        if (ProtocolEnum.class.isAssignableFrom(type)) {
            String identifier = null;
            String name = null;
            Integer value = null;

            if (args.containsKey("identifier")) {
                identifier = args.get("identifier").toString();
            }
            if (args.containsKey("name")) {
                name = args.get("name").toString();
            }
            if (args.containsKey("value")) {
                var obj = args.get("value");
                if (!Traits.isNumber(obj.getClass()) /* && !Traits.isNumberIfString(obj) */)
                    throw new MtsException("invalid value type for enum %s, it must be an int", type.getSimpleName());
                value = new NumberInfo(obj.toString()).intValue();
            }

            if (identifier == null && name == null && value == null) {
                throw new MtsException("specify identifier, name, or value for enum %s", type.getSimpleName());
            }

            if (identifier != null) {
                if (name != null || value != null)
                    throw new MtsException("specify exactly one of the following properties: identifier, name, or value for enum %s", type.getSimpleName());

                var res = ProtocolEnum.fromIdentifier((Class<? extends ProtocolEnum>) type, identifier);
                if (res == null)
                    throw new MtsException("invalid value for enum %s", type.getSimpleName());
                return (T) res;
            }

            if (value != null) {
                if (name != null)
                    throw new MtsException("specify exactly one of the following properties: identifier, name, or value for enum %s", type.getSimpleName());

                var res = ProtocolEnum.fromIntValue((Class<? extends ProtocolEnum>) type, value);
                if (res == null)
                    throw new MtsException("invalid value for enum %s", type.getSimpleName());
                return (T) res;
            }

            var res = ProtocolEnum.fromName((Class<? extends ProtocolEnum>) type, name);
            if (res.size() == 0)
                throw new MtsException("invalid value for enum %s", type.getSimpleName());
            if (res.size() > 1)
                throw new MtsException("multiple values matched for enum %s", type.getSimpleName());
            return (T) res.get(0);
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

        matched = Utils.streamToList(matched.stream().filter(constructor -> parameterTypeMatches(constructor, args)));
        if (matched.size() == 0) {
            throw new MtsException("no constructor found for given parameter values for type %s", type.getSimpleName());
        }
        if (matched.size() > 1) {
            var exactMatched = Utils.streamToList(matched.stream().filter(constructor -> parameterTypeExactMatches(constructor, args)));
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

            if (value instanceof ImplicitTypedValue) {
                value = construct(param.getType(), ((ImplicitTypedValue) value).getParameters());
            }

            var conversions = mtsConvert.convert(value, param.getType());

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
        } catch (Exception e) {
            throw new MtsException("Instantiation failed");
        }
    }
}
