package com.runsim.backend.otn;

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
    public String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"type\": \"object\",\"value\":{");
        Set<String> keys = keys();
        int i = 0;
        for (String key : keys) {
            sb.append(get(key).toJson());
            if (i != size() - 1)
                sb.append(",");
            i++;
        }
        sb.append("}");
        return sb.toString();
    }
}
