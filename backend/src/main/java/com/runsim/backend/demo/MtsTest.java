package com.runsim.backend.demo;

import com.runsim.backend.mts.MtsDecoder;
import com.runsim.backend.utils.Console;
import com.runsim.backend.utils.Json;
import com.runsim.backend.utils.Utils;

public class MtsTest {

    public static void main(String[] args) throws Exception {
        var jsonString = Utils.getResourceString("mts.json");

        var mtsDecoder = new MtsDecoder(false);
        var nasMessage = mtsDecoder.decode(jsonString);
        Console.println(Json.toJson(nasMessage));
    }
}
