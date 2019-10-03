package com.runsim.backend.otn;

public class OtnString extends OtnElement {
    private final String string;

    public OtnString(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
