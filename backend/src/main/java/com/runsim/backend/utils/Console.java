package com.runsim.backend.utils;

public class Console {

    public static void println(ConsoleColors color, Object... objects) {
        if (color == null)
            color = ConsoleColors.RESET;
        System.out.println(color + objectsToString(objects) + ConsoleColors.RESET);
    }

    public static void println(Object... objects) {
        System.out.println(objectsToString(objects));
    }

    public static void println() {
        System.out.println();
    }

    public static void printDiv() {
        println("-----------------------------------------------------------------------------");
    }

    private static String objectsToString(Object... objs) {
        var sb = new StringBuilder();
        int i = 0;
        for (var obj : objs) {
            sb.append(obj == null ? "null" : obj);
            if (i != objs.length - 1)
                sb.append(' ');
            i++;
        }
        return sb.toString();
    }
}
