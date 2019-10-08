package com.runsim.backend.otn;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.runsim.backend.json.Json;

public class OtnString extends OtnElement {
    private final String string;

    public OtnString(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }


    @Override
    public JsonElement toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type", new JsonPrimitive("string"));
        jsonObject.add("value", new JsonPrimitive(string));
        return jsonObject;
    }
}
