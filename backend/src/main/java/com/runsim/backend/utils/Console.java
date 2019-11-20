package com.runsim.backend.utils;

public class Console {

    public static void println(Color color, Object... objects) {
        if (color == null)
            color = Color.RESET;
        System.out.println(color + objectsToString(objects) + Color.RESET);
    }

    public static void println(Object... objects) {
        System.out.println(objectsToString(objects));
    }

    public static void println() {
        System.out.println();
    }

    public static void print(Object... objects) {
        System.out.print(objectsToString(objects));
    }

    public static void print(Color color, Object... objects) {
        if (color == null)
            color = Color.RESET;
        System.out.print(color + objectsToString(objects) + Color.RESET);
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
