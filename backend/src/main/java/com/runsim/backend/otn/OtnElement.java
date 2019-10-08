package com.runsim.backend.otn;

import com.google.gson.JsonElement;

public abstract class OtnElement {
    public abstract JsonElement toJson();

    @Override
    public String toString() {
        JsonElement jsonElement = toJson();
        return jsonElement == null ? "null" : jsonElement.toString();
    }
}
