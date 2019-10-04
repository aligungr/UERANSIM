package com.runsim.backend.otn;

import org.json.simple.JSONValue;

public class OtnString extends OtnElement {
    private final String string;

    public OtnString(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    @Override
    public String toJson() {
        return "{\"type\":\"string\",\"value\":\"" + JSONValue.escape(string) + "\"}";
    }
}
