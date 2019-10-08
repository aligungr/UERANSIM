package com.runsim.backend.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JElement {
    private JsonElement jsonElement;

    // Sadece içerden çağrılıyor JsonElement "null" literali olmayacağı garanti edilmektedir
    private JElement(JsonElement element) {
        if (element != null && element.isJsonNull())
            this.jsonElement = null;
        else this.jsonElement = element;
    }

    // Package private, null literali handle ediliyor
    static JElement fromJsonElement(JsonElement jsonElement) {
        if (jsonElement == null || jsonElement.isJsonNull()) return null;
        return new JElement(jsonElement);
    }

    public static JElement parse(String json) {
        return fromJsonElement(new JsonParser().parse(json));
    }

    /* ================ GetAs metodları ================ s*/

    public Integer getAsInt() {
        if (jsonElement == null) return null;
        if (jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isNumber())
            return jsonElement.getAsInt();
        return null;
    }

    public Long getAsLong() {
        if (jsonElement == null) return null;
        if (jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isNumber())
            return jsonElement.getAsLong();
        return null;
    }

    public String getAsString() {
        if (jsonElement == null) return null;
        if (jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isString())
            return jsonElement.getAsString();
        return null;
    }

    public Boolean getAsBool() {
        if (jsonElement == null) return null;
        if (jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isBoolean())
            return jsonElement.getAsBoolean();
        return null;
    }

    public JObject getAsObject() {
        if (jsonElement == null) return null;
        if (jsonElement.isJsonObject())
            return new JObject(jsonElement.getAsJsonObject());
        return null;
    }

    public JArray getAsArray() {
        if (jsonElement == null) return null;
        if (jsonElement.isJsonArray())
            return new JArray(jsonElement.getAsJsonArray());
        return null;
    }
}
