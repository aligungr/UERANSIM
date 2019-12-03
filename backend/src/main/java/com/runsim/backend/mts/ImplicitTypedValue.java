package com.runsim.backend.mts;

import java.util.LinkedHashMap;

final class ImplicitTypedValue {
    private LinkedHashMap<String, Object> parameters;

    public ImplicitTypedValue(LinkedHashMap<String, Object> parameters) {
        this.parameters = parameters;
    }

    public LinkedHashMap<String, Object> getParameters() {
        return parameters;
    }
}
