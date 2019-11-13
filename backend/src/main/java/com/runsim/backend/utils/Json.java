package com.runsim.backend.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.runsim.backend.utils.bits.*;
import com.runsim.backend.utils.octets.*;

public final class Json {
    private static final Gson gson;

    static {

        JsonSerializer<OctetN> octetNSerializer = (octetN, type, jsonSerializationContext)
                -> new JsonPrimitive(octetN.toString());
        JsonSerializer<BitN> bitNSerializer = (bitN, type, jsonSerializationContext)
                -> new JsonPrimitive(bitN.intValue());
        JsonSerializer<Bit> bitSerializer = (bit, type, jsonSerializationContext)
                -> new JsonPrimitive(bit.booleanValue());
        JsonSerializer<OctetString> octetStringSerializer = (octetString, type, jsonSerializationContext)
                -> new JsonPrimitive(octetString.toString());

        gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Octet.class, octetNSerializer)
                .registerTypeAdapter(Octet2.class, octetNSerializer)
                .registerTypeAdapter(Octet3.class, octetNSerializer)
                .registerTypeAdapter(Octet4.class, octetNSerializer)
                .registerTypeAdapter(OctetN.class, octetNSerializer)
                .registerTypeAdapter(Bit.class, bitSerializer)
                .registerTypeAdapter(Bit2.class, bitNSerializer)
                .registerTypeAdapter(Bit3.class, bitNSerializer)
                .registerTypeAdapter(Bit4.class, bitNSerializer)
                .registerTypeAdapter(Bit5.class, bitNSerializer)
                .registerTypeAdapter(Bit6.class, bitNSerializer)
                .registerTypeAdapter(Bit7.class, bitNSerializer)
                .registerTypeAdapter(BitN.class, bitNSerializer)
                .registerTypeAdapter(OctetString.class, octetStringSerializer)
                .create();
    }

    private Json() {
    }

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }
}
