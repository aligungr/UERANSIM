/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.utils.console;

import tr.havelsan.ueransim.utils.jcolor.AnsiColor;
import tr.havelsan.ueransim.utils.jcolor.AnsiColorFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BaseConsole {
    private final List<Consumer<String>> printHandlers = new ArrayList<>();
    private boolean standardPrintEnabled = true;

    public synchronized void print(AnsiColorFormat ansiColor, String format, Object... args) {
        String string = String.format(format, args);

        if (ansiColor == null) {
            output(string);
        } else {
            output(AnsiColor.colorize(string, ansiColor));
        }
    }

    public synchronized void println(AnsiColorFormat ansiColor, String format, Object... args) {
        String string = String.format(format, args);

        if (ansiColor == null) {
            outputLine(string);
        } else {
            outputLine(AnsiColor.colorize(string, ansiColor));
        }
    }

    public synchronized void printDiv() {
        println(null, "-----------------------------------------------------------------------------");
    }

    public void addPrintHandler(Consumer<String> handler) {
        printHandlers.add(handler);
    }

    public void setStandardPrintEnabled(boolean standardPrintEnabled) {
        this.standardPrintEnabled = standardPrintEnabled;
    }

    public synchronized void println() {
        outputLine();
    }

    private synchronized void outputLine() {
        outputLine("");
    }

    private synchronized void outputLine(String string) {
        output(String.format("%s%n", string));
    }

    private synchronized void output(String string) {
        if (standardPrintEnabled) {
            System.out.print(string);
        }

        for (var handler : printHandlers)
            handler.accept(string);
    }
}
