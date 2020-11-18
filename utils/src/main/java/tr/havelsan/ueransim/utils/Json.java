/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import tr.havelsan.ueransim.utils.bits.Bit;
import tr.havelsan.ueransim.utils.bits.BitN;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.OctetN;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.lang.reflect.Type;

// TODO: Gson is too slow, switch to another JSON library
public final class Json {
    private static Gson gson;

    private static void makeGson() {
        if (gson != null)
            return;

        JsonSerializer<OctetN> octetNSerializer = (octetN, type, jsonSerializationContext)
                -> new JsonPrimitive(octetN.toString());
        JsonSerializer<BitN> bitNSerializer = (bitN, type, jsonSerializationContext)
                -> new JsonPrimitive(bitN.intValue());
        JsonSerializer<Bit> bitSerializer = (bit, type, jsonSerializationContext)
                -> new JsonPrimitive(bit.intValue() != 0);
        JsonSerializer<OctetString> octetStringSerializer = (octetString, type, jsonSerializationContext)
                -> new JsonPrimitive(octetString.toString());
        JsonSerializer<BitString> bitStringSerializer = (bitString, type, jsonSerializationContext)
                -> new JsonPrimitive(bitString.toBinaryString());

        gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeHierarchyAdapter(OctetN.class, octetNSerializer)
                .registerTypeHierarchyAdapter(BitN.class, bitNSerializer)
                .registerTypeAdapter(Bit.class, bitSerializer)
                .registerTypeAdapter(OctetString.class, octetStringSerializer)
                .registerTypeAdapter(BitString.class, bitStringSerializer)
                .create();
    }

    public static String toJson(Object obj) {
        makeGson();
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        makeGson();
        return gson.fromJson(json, typeOfT);
    }
}
