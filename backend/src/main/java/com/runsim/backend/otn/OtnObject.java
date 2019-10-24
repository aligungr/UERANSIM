package com.runsim.backend.otn;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OtnObject extends OtnElement {
    private Map<String, OtnElement> fields;

    public OtnObject() {
        this.fields = new HashMap<>();
    }

    public OtnObject put(String key, OtnElement value) {
        fields.put(key, value);
        return this;
    }

    public OtnObject put(String key, int value) {
        fields.put(key, new OtnNumber(value));
        return this;
    }

    public OtnObject put(String key, float value) {
        fields.put(key, new OtnNumber(value));
        return this;
    }

    public OtnObject put(String key, String value) {
        fields.put(key, new OtnString(value));
        return this;
    }

    public int size() {
        return fields.size();
    }

    public OtnElement get(String key) {
        return fields.get(key);
    }

    public Set<String> keys() {
        return fields.keySet();
    }

    @Override
    public JsonElement toJson() {
        JsonObject value = new JsonObject();
        for (Map.Entry<String, OtnElement> entry : fields.entrySet())
            value.add(entry.getKey(), entry.getValue().toJson());

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type", new JsonPrimitive("object"));
        jsonObject.add("value", value);

        return jsonObject;
    }
}
