package com.runsim.backend.json;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public final class Json {

    private Json() { }

    public static String toJson(Object obj) {
        return new Gson().toJson(obj);
    }

    public static <T> T fromJson(String json, Class<?> klass) {
        try {
            return (T) new Gson().fromJson(json, klass);
        } catch (JsonSyntaxException e) {
            return null;
        }
    }
}
