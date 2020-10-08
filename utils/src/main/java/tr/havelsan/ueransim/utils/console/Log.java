package tr.havelsan.ueransim.utils.console;

import tr.havelsan.ueransim.utils.Severity;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.jcolor.AnsiColorFormat;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

// WARNING: This class is caller sensitive
public class Log {

    private static final ConcurrentHashMap<Long, Logger> loggers = new ConcurrentHashMap<>();

    public static void debug(Tag tag, String message, Object... args) {
        findLogger().debug(tag, message, args);
    }

    public static void info(Tag tag, String message, Object... args) {
        findLogger().info(tag, message, args);
    }

    public static void success(Tag tag, String message, Object... args) {
        findLogger().success(tag, message, args);
    }

    public static void warning(Tag tag, String message, Object... args) {
        findLogger().warning(tag, message, args);
    }

    public static void error(Tag tag, String message, Object... args) {
        findLogger().error(tag, message, args);
    }

    public static void funcIn(String name, Object... args) {
        findLogger().funcIn(name, args);
    }

    public static void funcOut() {
        findLogger().funcOut();
    }

    public static void log(Severity severity, AnsiColorFormat ansiColorFormat, int depth, Tag tag, String message, Object... args) {
        findLogger().log(severity, ansiColorFormat, depth, tag, message, args);
    }

    public static void addLogHandler(Consumer<LogEntry> handler) {
        findLogger().addLogHandler(handler);
    }

    public static void registerLogger(Thread thread, Logger logger) {
        loggers.put(thread.getId(), logger);
    }

    public static void unregisterLogger(Thread thread) {
        loggers.remove(thread.getId());
    }

    public static Logger getLoggerOrDefault(Thread thread) {
        return loggers.getOrDefault(thread.getId(), null);
    }

    private static Logger findLogger() {
        var logger = getLoggerOrDefault(Thread.currentThread());
        if (logger == null) {
            System.err.println("Smart Logger failure.");
            System.err.println("No logger registered for thread: " + Thread.currentThread().getName());
            System.exit(1);
        }
        return logger;
    }
}
