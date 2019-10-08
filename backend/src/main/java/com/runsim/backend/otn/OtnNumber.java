package com.runsim.backend.otn;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.math.BigDecimal;

public class OtnNumber extends OtnElement {
    private final BigDecimal value;

    public OtnNumber(BigDecimal value) {
        this.value = value;
    }

    public OtnNumber(String value) {
        this.value = new BigDecimal(value);
    }

    public OtnNumber(long value) {
        this.value = new BigDecimal(value);
    }

    public OtnNumber(double value) {
        this.value = new BigDecimal(value);
    }

    public int intValue() {
        return value.intValue();
    }

    public long longValue() {
        return value.longValue();
    }

    public float floatValue() {
        return value.floatValue();
    }

    public double doubleValue() {
        return value.doubleValue();
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public JsonElement toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type", new JsonPrimitive(value));
        return jsonObject;
    }
}
