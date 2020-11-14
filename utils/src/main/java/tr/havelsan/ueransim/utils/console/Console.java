/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.utils.console;

import tr.havelsan.ueransim.utils.jcolor.AnsiColorFormat;

import java.util.function.Consumer;

public class Console {

    private static final BaseConsole c = new BaseConsole();

    public static void print(AnsiColorFormat ansiColor, String format, Object... args) {
        c.print(ansiColor, format, args);
    }

    public static void println(AnsiColorFormat ansiColor, String format, Object... args) {
        c.println(ansiColor, format, args);
    }

    public static void printDiv() {
        c.printDiv();
    }

    public static void addPrintHandler(Consumer<String> handler) {
        c.addPrintHandler(handler);
    }

    public static void setStandardPrintEnabled(boolean standardPrintEnabled) {
        c.setStandardPrintEnabled(standardPrintEnabled);
    }

    public static void println() {
        c.println();
    }
}
