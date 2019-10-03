package com.runsim.backend.otn;

import java.util.HashMap;
import java.util.Map;

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
}
