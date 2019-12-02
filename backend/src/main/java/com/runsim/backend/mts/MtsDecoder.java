package com.runsim.backend.mts;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class MtsDecoder {
    private final boolean allowDeepConversion;

    public MtsDecoder(boolean allowDeepConversion) {
        this.allowDeepConversion = allowDeepConversion;
    }

    public <T> T decode(String json, Class<T> type) {
        return this.decode(new Gson().fromJson(json, JsonElement.class), type);
    }

    public <T> T decode(JsonElement json, Class<T> type) {
        if (json == null || json.isJsonNull())
            return null;
        if (json.isJsonPrimitive()) {
            return null; // todo
        } else if (json.isJsonArray()) {
            return null; // todo
        } else if (json.isJsonObject()) {
            return null; // todo
        } else {
            return null;
        }
    }
}
