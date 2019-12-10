package com.runsim.backend.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Console {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static boolean startOfLine = true;

    private static String getTime() {
        Calendar cal = Calendar.getInstance();
        return String.format("[%s] ", DATE_FORMAT.format(cal.getTime()));
    }

    public static void println(Color color, String format, Object... args) {
        if (color == null)
            color = Color.RESET;
        String string = String.format(format, args);
        if (startOfLine) {
            startOfLine = false;
            string = getTime() + string;
        }

        outputLine(color + string + Color.RESET);
        startOfLine = true;
    }

    public static void println(String format, Object... args) {
        String string = String.format(format, args);
        if (startOfLine) {
            startOfLine = false;
            string = getTime() + string;
        }

        outputLine(string);
        startOfLine = true;
    }

    public static void println() {
        outputLine();
        startOfLine = true;
    }

    public static void print(String format, Object... args) {
        String string = String.format(format, args);
        if (startOfLine) {
            startOfLine = false;
            string = getTime() + string;
        }

        output(string);
    }

    public static void print(Color color, String format, Object... args) {
        if (color == null)
            color = Color.RESET;
        String string = String.format(format, args);
        if (startOfLine) {
            startOfLine = false;
            string = getTime() + string;
        }

        output(color + string + Color.RESET);
    }

    public static void printDiv() {
        println("-----------------------------------------------------------------------------");
    }

    private synchronized static void outputLine() {
        outputLine("");
    }

    private synchronized static void outputLine(String string) {
        output(String.format("%s%n", string));
    }

    private synchronized static void output(String string) {
        System.out.print(string);
    }
}
