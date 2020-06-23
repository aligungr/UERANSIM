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
 *
 * @author Ali Güngör (aligng1620@gmail.com)
 */

package tr.havelsan.ueransim.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class Console {

    private static List<Consumer<String>> printHandlers = new ArrayList<>();
    private static Color lastColor;

    public static synchronized void print(Color color, String format, Object... args) {
        if (color == null) color = Color.RESET;

        String string = String.format(format, args);

        String s;

        if (!Objects.equals(lastColor, color)) {
            lastColor = color;
            s = color + string + Color.RESET;
        } else {
            s = string;
        }

        output(s);
    }

    public static synchronized void println(Color color, String format, Object... args) {
        if (color == null) color = Color.RESET;

        String string = String.format(format, args);

        String s;

        if (!Objects.equals(lastColor, color)) {
            lastColor = color;
            s = color + string + Color.RESET;
        } else {
            s = string;
        }

        outputLine(s);
    }

    public static synchronized void printDiv() {
        println(lastColor, "-----------------------------------------------------------------------------");
    }

    public synchronized static void addPrintHandler(Consumer<String> handler) {
        printHandlers.add(handler);
    }

    public static synchronized void println() {
        outputLine();
    }

    private synchronized static void outputLine() {
        outputLine("");
    }

    private synchronized static void outputLine(String string) {
        output(String.format("%s%n", string));
    }

    private synchronized static void output(String string) {
        System.out.print(string);

        for (var handler : printHandlers)
            handler.accept(string);
    }
}
