package com.runsim.backend.json;

import com.google.gson.JsonObject;

import java.util.Set;

public class JObject {

    private JsonObject jobj;

    JObject(JsonObject jobj) {
        this.jobj = jobj;
    }

    public static JObject parse(String json) {
        try {
            JElement element = JElement.parse(json);
            return element == null ? null : element.getAsObject();
        } catch (Exception e) {
            return null;
        }
    }

    public Integer getInt(String memberName) {
        JElement element = get(memberName);
        return element == null ? null : element.getAsInt();
    }

    public Long getLong(String memberName) {
        JElement element = get(memberName);
        return element == null ? null : element.getAsLong();
    }

    public String getString(String memberName) {
        JElement element = get(memberName);
        return element == null ? null : element.getAsString();
    }

    public Boolean getBool(String memberName) {
        JElement element = get(memberName);
        return element == null ? null : element.getAsBool();
    }

    public JObject getObject(String memberName) {
        JElement element = get(memberName);
        return element == null ? null : element.getAsObject();
    }

    public JArray getArray(String memberName) {
        JElement element = get(memberName);
        return element == null ? null : element.getAsArray();
    }

    public JElement get(String memberName) {
        return JElement.fromJsonElement(jobj.get(memberName));
    }

    public int size() {
        return jobj.size();
    }

    public Set<String> keys() {
        return jobj.keySet();
    }

    @Override
    public String toString() {
        return jobj == null ? "null" : jobj.toString();
    }
}
