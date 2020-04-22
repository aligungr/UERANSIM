package tr.havelsan.ueransim.utils;

public class Logging {

    public static void debug(String tag, String message, Object... args) {
        log(Severity.DEBUG, tag, message, args);
    }

    public static void info(String tag, String message, Object... args) {
        log(Severity.INFO, tag, message, args);
    }

    public static void success(String tag, String message, Object... args) {
        log(Severity.SUCCESS, tag, message, args);
    }

    public static void warning(String tag, String message, Object... args) {
        log(Severity.WARNING, tag, message, args);
    }

    public static void error(String tag, String message, Object... args) {
        log(Severity.ERROR, tag, message, args);
    }

    public static void funcIn(String tag, String message, Object... args) {
        log(Severity.FUNC_IN, tag, message, args);
    }

    public static void funcOut(String tag, String message, Object... args) {
        log(Severity.FUNC_OUT, tag, message, args);
    }

    public static void log(Severity severity, String tag, String message, Object... args) {
        if (severity == null) severity = Severity.DEBUG;
        if (tag == null) tag = "";
        if (message == null) message = "";
        if (args == null) args = new Object[0];


    }
}
