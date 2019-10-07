package com.runsim.backend.otn;

public abstract class OtnElement {
    public abstract String toJson();

    @Override
    public String toString() {
        return toJson();
    }
}
