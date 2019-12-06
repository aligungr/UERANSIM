package com.runsim.backend.mts;

import java.util.LinkedHashMap;

public final class ImplicitTypedObject {
    private LinkedHashMap<String, Object> parameters;

    public ImplicitTypedObject(LinkedHashMap<String, Object> parameters) {
        this.parameters = parameters;
    }

    public LinkedHashMap<String, Object> getParameters() {
        return parameters;
    }
}
