package com.runsim.backend.otn;

public class OtnNumber extends OtnElement {
    private final int intValue;
    private final float floatValue;
    private final boolean isInt;

    public OtnNumber(int intValue) {
        this.intValue = intValue;
        this.floatValue = 0;
        this.isInt = true;
    }

    public OtnNumber(float floatValue) {
        this.intValue = 0;
        this.floatValue = floatValue;
        this.isInt = false;
    }

    public int getInt() {
        return intValue;
    }

    public float getFloat() {
        return floatValue;
    }

    public boolean isInteger() {
        return isInt;
    }
}
