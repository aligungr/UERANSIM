package com.runsim.backend.demo;

import com.runsim.backend.mts.MtsConvert;
import com.runsim.backend.mts.Point;
import com.runsim.backend.utils.Color;
import com.runsim.backend.utils.Console;

public class MtsTest {

    public static void main(String[] args) throws Exception {
        MtsConvert.convert(
                (byte) 5,
                Point.class
        ).forEach(conversion -> {
            Console.println(Color.YELLOW, "depth:", conversion.depth);
            Console.println(Color.GREEN, "level:", conversion.level);
            Console.println(Color.BLUE, conversion.value);
            Console.println();
        });
    }
}
