package com.runsim.backend.demo;

import com.runsim.backend.mts.MtsDecoder;
import com.runsim.backend.mts.TypeRegistry;
import com.runsim.backend.nas.impl.enums.EMobileCountryCode;
import com.runsim.backend.nas.mts.MtsProtocolEnumRegistry;
import com.runsim.backend.utils.Console;
import com.runsim.backend.utils.Json;
import com.runsim.backend.utils.Utils;

public class MtsTest {

    public static void main(String[] args) throws Exception {
        TypeRegistry.registerTypeName("Sınıf", Sınıf.class);
        TypeRegistry.registerTypeName("EMobileCountryCode", EMobileCountryCode.class);
        TypeRegistry.registerCustomType(new MtsProtocolEnumRegistry());

        var jsonString = Utils.getResourceString("mts.json");

        var mtsDecoder = new MtsDecoder(false);
        var nasMessage = mtsDecoder.decode(jsonString);
        Console.println(Json.toJson(nasMessage));
    }

    public static class Sınıf {
        final EMobileCountryCode mcc1;
        final EMobileCountryCode mcc2;

        public Sınıf(EMobileCountryCode mcc1, EMobileCountryCode mcc2) {
            this.mcc1 = mcc1;
            this.mcc2 = mcc2;
        }
    }
}
