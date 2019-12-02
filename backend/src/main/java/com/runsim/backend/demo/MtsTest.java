package com.runsim.backend.demo;

import com.runsim.backend.mts.MtsDecoder;
import com.runsim.backend.nas.core.messages.NasMessage;
import com.runsim.backend.utils.Console;
import com.runsim.backend.utils.Json;
import com.runsim.backend.utils.Utils;

public class MtsTest {

    public static void main(String[] args) throws Exception {
        var jsonString = Utils.getResourceString("mts.json");

        var mtsDecoder = new MtsDecoder(false);
        var nasMessage = mtsDecoder.decode(jsonString, NasMessage.class);
        Console.println(Json.toJson(nasMessage));

        /*


        TypeRegistry.register("Octet2", Octet2.class);

        var res = MtsConstruct.construct(Octet2.class, new HashMap<>() {{
            put("msb", 0xA);
            put("lsb", 0xB);
        }}, false);

        Console.println(res.toString());*/
    }

}
