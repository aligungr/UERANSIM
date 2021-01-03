/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import tr.havelsan.ueransim.app.common.sw.SocketWrapper;

public class SocketWrapperSerializer {
    private static final Gson gson = new GsonBuilder().create();

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> typeOfT) {
        var obj = gson.fromJson(json, JsonObject.class);
        var type = obj.get("type").getAsString();
        Class<?> cls;
        try {
            cls = Class.forName(SocketWrapper.class.getPackageName() + "." + type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (T) gson.fromJson(json, cls);
    }
}
