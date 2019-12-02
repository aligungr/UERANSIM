package com.runsim.backend.demo;

import com.runsim.backend.mts.MtsConstruct;
import com.runsim.backend.mts.TypeRegistry;
import com.runsim.backend.utils.Console;
import com.runsim.backend.utils.octets.Octet2;

import java.util.HashMap;

public class MtsTest {

    public static void main(String[] args) throws Exception {
        TypeRegistry.register("Octet2", Octet2.class);

        var res = MtsConstruct.construct(Octet2.class, new HashMap<>() {{
            put("msb", 0xA);
            put("lsb", 0xB);
        }}, false);

        Console.println(res.toString());
    }
}
