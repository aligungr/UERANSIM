package com.runsim.backend.mts;

import com.runsim.backend.exceptions.MtsException;
import com.runsim.backend.utils.Utils;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Map;

public class MtsConstruct {

    private static boolean parameterCountMatches(Constructor<?> constructor, Map<String, Object> parameters) {
        return constructor.getParameterCount() == parameters.size();
    }

    private static boolean parameterNameMatches(Constructor<?> constructor, Map<String, Object> parameters) {
        if (constructor.getParameterCount() != parameters.size())
            return false;
        for (var param : constructor.getParameters()) {
            if (!parameters.containsKey(param.getName()))
                return false;
        }
        return true;
    }

    public static <T> T construct(Class<T> type, Map<String, Object> args) {
        var constructors = Arrays.asList(type.getDeclaredConstructors());

        var matched = Utils.streamToList(constructors.stream().filter(constructor -> parameterCountMatches(constructor, args)));
        if (matched.size() == 0) {
            throw new MtsException("no constructor found for %d number of parameters", args.size());
        }

        matched = Utils.streamToList(matched.stream().filter(constructor -> parameterNameMatches(constructor, args)));
        if (matched.size() == 0) {
            throw new MtsException("no constructor found given parameter names");
        }
        if (matched.size() > 1) {
            throw new MtsException("multiple constructors found for given parameter names");
        }

        var constructor = matched.get(0);

        var params = constructor.getParameters();
        var paramInstances = new Object[params.length];

        for (int i = 0; i < params.length; i++) {
            var param = params[i];
            var value = args.get(param.getName());
            var conversions = MtsConvert.convert(value, param.getType());

            var shallowConversions = Utils.streamToList(conversions.stream().filter(conversion -> conversion.depth == 0));
            var deepConversions = Utils.streamToList(conversions.stream().filter(conversion -> conversion.depth != 0));

            if (shallowConversions.size() == 0 && deepConversions.size() == 0) {
                throw new MtsException("'%s' parameter value has type '%s', but expected type is '%s' or convertable types.", param.getName(), TypeRegistry.getClassName(value.getClass()), param.getType().getSimpleName());
            }

            Conversion<?> selectedConversion;

            if (shallowConversions.size() == 0) {
                if (deepConversions.size() > 1) {
                    throw new MtsException("multiple constructors matched for parameter '%s'", param.getName());
                }
                selectedConversion = deepConversions.get(0);
            } else if (shallowConversions.size() > 1) {
                throw new MtsException("multiple constructors matched for parameter '%s'", param.getName());
            } else {
                selectedConversion = shallowConversions.get(0);
            }

            paramInstances[i] = selectedConversion.value;
        }

        try {
            return (T) constructor.newInstance(paramInstances);
        } catch (Exception e) {
            throw new MtsException("cannot call constructor. %s", e.toString());
        }
    }
}
