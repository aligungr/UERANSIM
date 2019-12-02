package com.runsim.backend.mts;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.runsim.backend.exceptions.MtsException;

import java.util.LinkedHashMap;

public class MtsDecoder {
    private final boolean allowDeepConversion;

    public MtsDecoder(boolean allowDeepConversion) {
        this.allowDeepConversion = allowDeepConversion;
    }

    public Object decode(String json) {
        return this.decode(new Gson().fromJson(json, JsonElement.class));
    }

    public Object decode(JsonElement json) {
        if (json == null || json.isJsonNull())
            return null;
        if (json.isJsonPrimitive()) {
            var jsonPrimitive = json.getAsJsonPrimitive();
            if (jsonPrimitive.isString()) {
                return jsonPrimitive.getAsString();
            } else if (jsonPrimitive.isNumber()) {
                return jsonPrimitive.getAsBigDecimal();
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

            return new ValueDescriptor(typeName, properties);
        } else {
            return null; // not possible
        }
    }
}
