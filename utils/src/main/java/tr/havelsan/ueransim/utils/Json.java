/*
 * MIT License
 *
 * Copyright (c) 2020 ALİ GÜNGÖR
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * @author Ali Güngör (aligng1620@gmail.com)
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
