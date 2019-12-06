package com.runsim.backend.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.runsim.backend.utils.bits.Bit;
import com.runsim.backend.utils.bits.BitN;
import com.runsim.backend.utils.octets.OctetN;
import com.runsim.backend.utils.octets.OctetString;

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

        gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeHierarchyAdapter(OctetN.class, octetNSerializer)
                .registerTypeHierarchyAdapter(BitN.class, bitNSerializer)
                .registerTypeAdapter(Bit.class, bitSerializer)
                .registerTypeAdapter(OctetString.class, octetStringSerializer)
                .create();
    }

    public static String toJson(Object obj) {
        makeGson();
        return gson.toJson(obj);
    }
}
