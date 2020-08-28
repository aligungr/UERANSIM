/*
 * MIT License
 *
 * Copyright (c) 2020 ALİ GÜNGÖR
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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
