package tr.havelsan.ueransim.utils;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class Logging {

    public static AtomicInteger functionDepth = new AtomicInteger(0);

    public static void debug(String tag, String message, Object... args) {
        log(Severity.DEBUG, tag, Color.WHITE_BRIGHT, functionDepth.get(), message, args);
    }

    public static void info(String tag, String message, Object... args) {
        log(Severity.INFO, tag, Color.WHITE_BRIGHT, functionDepth.get(), message, args);
    }

    public static void success(String tag, String message, Object... args) {
        log(Severity.SUCCESS, tag, Color.GREEN_BRIGHT, functionDepth.get(), message, args);
    }

    public static void warning(String tag, String message, Object... args) {
        log(Severity.WARNING, tag, Color.YELLOW_BRIGHT, functionDepth.get(), message, args);
    }

    public static void error(String tag, String message, Object... args) {
        log(Severity.ERROR, tag, Color.RED_BRIGHT, functionDepth.get(), message, args);
    }

    public static void funcIn(String name, Object... args) {
        log(Severity.FUNC_IN, "FUNCTION_ENTER", Color.WHITE_BRIGHT, functionDepth.incrementAndGet(), name, args);
    }

    public static void funcOut(String name, Object... args) {
        log(Severity.FUNC_OUT, "FUNCTION_EXIT", Color.WHITE_BRIGHT, functionDepth.decrementAndGet(), name, args);
    }

    public static void log(Severity severity, String tag, Color color, int depth, String message, Object... args) {
        if (severity == null) severity = Severity.DEBUG;
        if (tag == null) tag = "";
        if (message == null) message = "";
        if (args == null) args = new Object[0];

        String str = String.format(Locale.ENGLISH, message, args);
        String display = String.format(Locale.ENGLISH, "[%s] [%s]%s %s",
                severity, tag, " ".repeat(Math.max(0, depth)), str);
        Console.println(color, display);
    }
}
