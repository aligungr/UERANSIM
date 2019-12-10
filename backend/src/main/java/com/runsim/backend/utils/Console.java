package com.runsim.backend.utils;

public class Console {

    public static void println(Color color, String format, Object... args) {
        if (color == null)
            color = Color.RESET;
        System.out.println(color + String.format(format, args) + Color.RESET);
    }

    public static void println(String format, Object... args) {
        System.out.println(String.format(format, args));
    }

    public static void println() {
        System.out.println();
    }

    public static void print(String format, Object... args) {
        System.out.print(String.format(format, args));
    }

    public static void print(Color color, String format, Object... args) {
        if (color == null)
            color = Color.RESET;
        System.out.print(color + String.format(format, args) + Color.RESET);
    }

    public static void printDiv() {
        println("-----------------------------------------------------------------------------");
    }
}
