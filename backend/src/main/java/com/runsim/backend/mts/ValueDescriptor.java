package com.runsim.backend.mts;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class ValueDescriptor {
    private String typeName;
    private LinkedHashMap<String, Object> parameters;

    public ValueDescriptor(String typeName, LinkedHashMap<String, Object> parameters) {
        this.typeName = typeName;
        this.parameters = parameters;
    }

    public String getTypeName() {
        return typeName;
    }

    public HashMap<String, Object> getParameters() {
        return parameters;
    }

    public int getParameterCount() {
        return parameters.size();
    }

    public String[] getParameterNames() {
        return parameters.keySet().toArray(new String[0]);
    }
}
