package com.runsim.backend.mts;

import com.runsim.backend.utils.Color;
import com.runsim.backend.utils.Console;

public class MtsTest {

    public static void main(String[] args) throws Exception {
        MtsInfo.convert(
                (byte) 5,
                Point.class
        ).forEach(conversion -> {
            Console.println(Color.YELLOW, conversion.depth);
            Console.println(Color.GREEN, conversion.level);
            Console.println(Color.BLUE, conversion.value);
            Console.println();
        });
    }
}
