/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.mts;

import com.google.common.base.CaseFormat;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import tr.havelsan.ueransim.utils.IFileProvider;

import java.io.File;
import java.util.LinkedHashMap;

public class MtsDecoder {

    private final MtsContext ctx;
    private IFileProvider fileProvider = (searchDir, path) -> null;

    MtsDecoder(MtsContext ctx) {
        this.ctx = ctx;
    }

    public void setFileProvider(IFileProvider fileProvider) {
        this.fileProvider = fileProvider;
    }

    public Object decode(String filePath) {
        var json = fileProvider.readFile("", filePath);
        if (json == null)
            throw new MtsException("referenced file not found: %s", filePath);

        var jsonElement = parseJson(json);
        jsonElement = resolveJsonRefs(dirPath(filePath), jsonElement);
        return decode(jsonElement);
    }

    private JsonElement parseJson(String json) {
        return new Gson().fromJson(json, JsonElement.class);
    }

    private String dirPath(String filePath) {
        String path = new File(filePath).getParent();
        return path == null ? "./" : path;
    }

    private JsonElement resolveJsonRefs(String searchDir, JsonElement element) {
        if (element == null) {
            return null;
        } else if (element.isJsonNull()) {
            return element;
        } else if (element.isJsonPrimitive()) {
            // for reference jsons
            if (element.getAsJsonPrimitive().isString()) {
                String string = element.getAsString();
                if (string != null && string.startsWith("@")) {
                    string = string.substring(1);
                    if (string.length() == 0)
                        throw new MtsException("@ref value cannot be empty");

                    var refContent = fileProvider.readFile(searchDir, string);
                    if (refContent == null)
                        throw new MtsException("referenced file not found: %s", string);

                    return resolveJsonRefs(dirPath(refContent), parseJson(refContent));
                }
            }
            // for all other primitives
            return element;
        } else if (element.isJsonArray()) {
            var jsonArray = element.getAsJsonArray();
            var newJsonArray = new JsonArray();
            for (int i = 0; i < jsonArray.size(); i++) {
                newJsonArray.add(resolveJsonRefs(searchDir, jsonArray.get(i)));
            }
            return newJsonArray;
        } else if (element.isJsonObject()) {
            var jsonObject = element.getAsJsonObject();
            if (jsonObject.has("@ref")) {
                var refElement = jsonObject.get("@ref");
                if (!refElement.isJsonPrimitive() || !refElement.getAsJsonPrimitive().isString())
                    throw new MtsException("@ref value must be string");

                var refValue = refElement.getAsString();
                if (refValue == null || refValue.length() == 0)
                    throw new MtsException("@ref value cannot be empty");

                var refContent = fileProvider.readFile(searchDir, refValue);
                if (refContent == null)
                    throw new MtsException("referenced file not found: %s", refValue);

                var refJson = parseJson(refContent);
                refJson = resolveJsonRefs(dirPath(refContent), refJson);
                if (refJson == null)
                    return null;
                else if (refJson.isJsonObject()) {
                    var refObject = refJson.getAsJsonObject();
                    for (var entry : jsonObject.entrySet()) {
                        if (entry.getKey().equals("@ref"))
                            continue;
                        if (refObject.has(entry.getKey())) {
                            refObject.remove(entry.getKey());
                        }
                        refObject.add(entry.getKey(), resolveJsonRefs(dirPath(refContent), entry.getValue()));
                    }
                    return refObject;
                } else {
                    if (jsonObject.size() != 1)
                        throw new MtsException("primitive json element cannot be overridden, remove properties other than @ref");
                    return refJson;
                }
            } else {
                var newJsonObject = new JsonObject();
                for (var entry : jsonObject.entrySet()) {
                    newJsonObject.add(entry.getKey(), resolveJsonRefs(searchDir, entry.getValue()));
                }
                return newJsonObject;
            }
        } else {
            return null;
        }
    }

    public Object decode(JsonElement json) {
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
                return null;
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

            var typeDecl = jsonObject.has(ctx.getTypeKeyword()) ? jsonObject.get(ctx.getTypeKeyword()) : null;
            if (typeDecl != null) {
                if (!typeDecl.isJsonPrimitive() || !typeDecl.getAsJsonPrimitive().isString()) {
                    throw new MtsException("invalid type declaration");
                }
                typeName = typeDecl.getAsString();
            }

            var properties = new LinkedHashMap<String, Object>();
            for (var entry : jsonObject.entrySet()) {
                if (entry.getKey().equals(ctx.getTypeKeyword())) {
                    // do nothing
                } else if (entry.getKey().startsWith("@")) {
                    throw new MtsException("unrecognized keyword: %s", entry.getKey());
                } else {
                    String key = entry.getKey();
                    if (ctx.isKebabCaseDecoding()) {
                        key = CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, key);
                    }
                    properties.put(key, decode(entry.getValue()));
                }
            }

            if (typeName != null) {
                var type = ctx.typeRegistry.getClassByName(typeName);
                if (type == null) {
                    throw new MtsException("declared type not registered: %s", typeName);
                }
                return ctx.constructor.construct(type, properties, true);
            } else {
                return new ImplicitTypedObject(properties);
            }
        } else {
            return null;
        }
    }
}
