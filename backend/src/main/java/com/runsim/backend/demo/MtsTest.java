package com.runsim.backend.demo;

import com.runsim.backend.mts.MtsConstruct;
import com.runsim.backend.mts.Point;
import com.runsim.backend.mts.TypeRegistry;
import com.runsim.backend.utils.Console;

import java.util.ArrayList;
import java.util.HashMap;

public class MtsTest {

    public static void main(String[] args) throws Exception {
        TypeRegistry.register("ArrayListtt", ArrayList.class);

        var point = MtsConstruct.construct(Point.class, new HashMap<>() {{
            put("x", "6");
            put("y", 3);
        }});

        Console.println(point.toString());



        /*
        MtsConvert.convert(
                "true",
                Point.class
        ).forEach(conversion -> {
            Console.println(Color.YELLOW, "depth:", conversion.depth);
            Console.println(Color.GREEN, "level:", conversion.level);
            Console.println(Color.BLUE, conversion.value);
            Console.println();
        });*/
    }
}
