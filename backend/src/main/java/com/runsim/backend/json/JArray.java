package com.runsim.backend.json;

import com.google.gson.JsonArray;

public class JArray {

    private JsonArray jarr;

    JArray(JsonArray jarr) {
        this.jarr = jarr;
    }

    public static JArray parse(String json) {
        try {
            JElement element = JElement.parse(json);
            return element == null ? null : element.getAsArray();
        } catch (Exception e) {
            return null;
        }
    }

    public int size() {
        return jarr.size();
    }

    public Integer getInt(int index) {
        JElement element = JElement.fromJsonElement(jarr.get(index));
        return element == null ? null : element.getAsInt();
    }

    public String getString(int index) {
        JElement element = JElement.fromJsonElement(jarr.get(index));
        return element == null ? null : element.getAsString();
    }

    public Boolean getBool(int index) {
        JElement element = JElement.fromJsonElement(jarr.get(index));
        return element == null ? null : element.getAsBool();
    }

    public JObject getObject(int index) {
        JElement element = JElement.fromJsonElement(jarr.get(index));
        return element == null ? null : element.getAsObject();
    }

    public JArray getArray(int index) {
        JElement element = JElement.fromJsonElement(jarr.get(index));
        return element == null ? null : element.getAsArray();
    }

    public JElement get(int index) {
        return JElement.fromJsonElement(jarr.get(index));
    }
}
