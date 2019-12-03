package com.runsim.backend.mts;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.runsim.backend.exceptions.MtsException;

import java.util.LinkedHashMap;

public class MtsDecoder {

    public static Object decode(String json) {
        return decode(new Gson().fromJson(json, JsonElement.class));
    }

    public static Object decode(JsonElement json) {
        if (json == null || json.isJsonNull())
            return null;
        if (json.isJsonPrimitive()) {
            var jsonPrimitive = json.getAsJsonPrimitive();
            if (jsonPrimitive.isString()) {
                return jsonPrimitive.getAsString();
            } else if (jsonPrimitive.isNumber()) {
                var jsonNumber = new NumberInfo(jsonPrimitive.getAsBigDecimal());
                if (jsonNumber.isInt())
                    return jsonNumber.intValue();
                if (jsonNumber.isLong())
                    return jsonNumber.longValue();
                if (jsonNumber.isFloat())
                    return jsonNumber.floatValue();
                if (jsonNumber.isDouble())
                    return jsonNumber.doubleValue();
                if (jsonNumber.isWholeNumber())
                    return jsonNumber.bigIntegerValue();
                return jsonNumber.bigDecimalValue();
            } else if (jsonPrimitive.isBoolean()) {
                return jsonPrimitive.getAsBoolean();
            } else {
                return null; // not possible
            }
        } else if (json.isJsonArray()) {
            var jsonArray = json.getAsJsonArray();
            var array = new Object[jsonArray.size()];
            for (int i = 0; i < array.length; i++) {
                array[i] = decode(jsonArray.get(i));
            }
            return array;
        } else if (json.isJsonObject()) {
            var jsonObject = json.getAsJsonObject();
            String typeName = null;

            var typeDecl = jsonObject.has("@type") ? jsonObject.get("@type") : null;
            if (typeDecl != null) {
                if (!typeDecl.isJsonPrimitive() || !typeDecl.getAsJsonPrimitive().isString()) {
                    throw new MtsException("invalid type declaration");
                }
                typeName = typeDecl.getAsString();
            }

            var properties = new LinkedHashMap<String, Object>();
            for (var entry : jsonObject.entrySet()) {
                if (!entry.getKey().startsWith("@"))
                    properties.put(entry.getKey(), decode(entry.getValue()));
            }

            if (typeName != null) {
                var type = TypeRegistry.getClassByName(typeName);
                if (type == null) {
                    throw new MtsException("declared type not registered: %s", typeName);
                }
                return MtsConstruct.construct(type, properties);
            } else {
                return new ImplicitTypedValue(properties);
            }
        } else {
            return null; // not possible
        }
    }
}
