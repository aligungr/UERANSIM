package com.runsim.backend.demo;

import com.runsim.backend.mts.MtsDecoder;
import com.runsim.backend.mts.TypeRegistry;
import com.runsim.backend.nas.impl.ies.IEImeiMobileIdentity;
import com.runsim.backend.nas.impl.messages.IdentityResponse;
import com.runsim.backend.utils.Console;
import com.runsim.backend.utils.Json;
import com.runsim.backend.utils.Utils;
import com.runsim.backend.utils.octets.Octet;
import com.runsim.backend.utils.octets.Octet2;

public class MtsTest {

    public static void main(String[] args) throws Exception {
        var jsonString = Utils.getResourceString("mts.json");

        TypeRegistry.register("IdentityResponse", IdentityResponse.class);
        TypeRegistry.register("IEImeiMobileIdentity", IEImeiMobileIdentity.class);
        TypeRegistry.register("Octet2", Octet2.class);
        TypeRegistry.register("Octet", Octet.class);

        var mtsDecoder = new MtsDecoder(false);
        var nasMessage = mtsDecoder.decode(jsonString);
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
