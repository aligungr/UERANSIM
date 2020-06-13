package tr.havelsan.ueransim.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class Logging {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static final AtomicInteger functionDepth = new AtomicInteger(0);

    public static void debug(Tag tag, String message, Object... args) {
        log(Severity.DEBUG, Color.WHITE_BRIGHT, functionDepth.get(), tag, message, args);
    }

    public static void info(Tag tag, String message, Object... args) {
        log(Severity.INFO, Color.WHITE_BRIGHT, functionDepth.get(), tag, message, args);
    }

    public static void success(Tag tag, String message, Object... args) {
        log(Severity.SUCCESS, Color.GREEN_BRIGHT, functionDepth.get(), tag, message, args);
    }

    public static void warning(Tag tag, String message, Object... args) {
        log(Severity.WARNING, Color.YELLOW_BRIGHT, functionDepth.get(), tag, message, args);
    }

    public static void error(Tag tag, String message, Object... args) {
        log(Severity.ERROR, Color.RED_BRIGHT, functionDepth.get(), tag, message, args);
    }

    public static void funcIn(String name, Object... args) {
        log(Severity.FUNC_IN, Color.WHITE_BRIGHT, functionDepth.getAndIncrement(), null, name, args);
    }

    public static void funcOut() {
        log(Severity.FUNC_OUT, Color.WHITE_BRIGHT, functionDepth.decrementAndGet(), null, "");
    }

    public static void log(Severity severity, Color color, int depth, Tag tag, String message, Object... args) {
        if (severity == null) severity = Severity.DEBUG;
        if (message == null) message = "";
        if (args == null) args = new Object[0];

        String spacing = (" ").repeat(Math.max(0, depth * 2));
        String tagging = tag == null ? "" : "[" + tag + "] ";

        String str = String.format(Locale.ENGLISH, message, args);
        String display = String.format(Locale.ENGLISH, "%s%s[%s] %s%s", getTime(), spacing, severity, tagging, str);
        Console.println(color, display);
    }

    private static String getTime() {
        Calendar cal = Calendar.getInstance();
        return String.format("[%s] ", DATE_FORMAT.format(cal.getTime()));
    }
}
