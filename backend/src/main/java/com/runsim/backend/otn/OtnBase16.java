package com.runsim.backend.otn;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.runsim.backend.Utils;

public class OtnBase16 extends OtnElement {
    private final String base16;

    public OtnBase16(String base16) {
        if (!Utils.isValidHexString(base16))
            throw new RuntimeException("invalid base16: " + base16);
        this.base16 = base16;
    }

    public String getString() {
        return base16;
    }

    public byte[] getData() {
        return Utils.hexStringToByteArray(base16);
    }

    @Override
    public JsonElement toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type", new JsonPrimitive("base16"));
        jsonObject.add("value", new JsonPrimitive(base16));
        return jsonObject;
    }
}
