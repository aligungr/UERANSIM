package com.runsim.backend.otn;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OtnObject extends OtnElement {
    private Map<String, OtnElement> fields;

    public OtnObject() {
        this.fields = new HashMap<>();
    }

    public OtnObject put(String key, OtnElement element) {
        fields.put(key, element);
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
