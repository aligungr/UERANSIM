package tr.havelsan.ueransim.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class BaseConsole {
    private final List<Consumer<String>> printHandlers = new ArrayList<>();
    private boolean standardPrintEnabled = true;
    private Color lastColor;

    public synchronized void print(Color color, String format, Object... args) {
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

    public synchronized void println(Color color, String format, Object... args) {
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

    public synchronized void printDiv() {
        println(lastColor, "-----------------------------------------------------------------------------");
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
