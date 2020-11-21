/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.utils.console;

import tr.havelsan.ueransim.utils.Constants;
import tr.havelsan.ueransim.utils.Severity;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.exceptions.FatalTreatedErrorException;
import tr.havelsan.ueransim.utils.jcolor.AnsiColor;
import tr.havelsan.ueransim.utils.jcolor.AnsiColorFormat;
import tr.havelsan.ueransim.utils.jcolor.AnsiPalette;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class Logger {

    public static final Logger GLOBAL = new Logger("global");

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private final List<Consumer<LogEntry>> printHandlers = new ArrayList<>();
    private final AtomicInteger functionDepth = new AtomicInteger(0);
    private final BaseConsole console = new BaseConsole();
    private final String loggerName;

    public Logger(String loggerName) {
        this.loggerName = loggerName;
    }

    private static String getTime() {
        Calendar cal = Calendar.getInstance();
        return String.format("%s ", DATE_FORMAT.format(cal.getTime()));
    }

    private static Object[] concat(Object o, Object[] arr) {
        // NOTE: I hate Java.
        var a = new Object[arr.length + 1];
        a[0] = o;
        System.arraycopy(arr, 0, a, 1, arr.length);
        return a;
    }

    private static boolean shouldDispatch(Severity severity, Tag tag) {
        if (severity != null) {
            if (severity == Severity.DEBG)
                return false;
            if (severity == Severity.ERRO || severity == Severity.SUCC)
                return true;
            if (severity == Severity.FUNI || severity == Severity.FUNO)
                return false;
        }
        if (tag != null) {
            if (tag == Tag.MSG)
                return false;
            if (tag == Tag.NASSEC)
                return false;
        }
        return true;
    }

    public BaseConsole getConsole() {
        return console;
    }

    public void debug(Tag tag, String message, Object... args) {
        log(Severity.DEBG, AnsiPalette.PAINT_LOG_NORMAL, functionDepth.get(), tag, message, args);
    }

    public void info(Tag tag, String message, Object... args) {
        log(Severity.INFO, AnsiPalette.PAINT_LOG_NORMAL, functionDepth.get(), tag, message, args);
    }

    public void success(Tag tag, String message, Object... args) {
        log(Severity.SUCC, AnsiPalette.PAINT_LOG_SUCCESS, functionDepth.get(), tag, message, args);
    }

    public void warning(Tag tag, String message, Object... args) {
        log(Severity.WARN, AnsiPalette.PAINT_LOG_WARNING, functionDepth.get(), tag, message, args);
    }

    public void error(Tag tag, String message, Object... args) {
        log(Severity.ERRO, AnsiPalette.PAINT_LOG_ERROR, functionDepth.get(), tag, message, args);
    }

    public void funcIn(String name, Object... args) {
        log(Severity.FUNI, AnsiPalette.PAINT_LOG_NORMAL, functionDepth.getAndIncrement(), null, name, args);
    }

    public void funcOut() {
        log(Severity.FUNO, AnsiPalette.PAINT_LOG_NORMAL, functionDepth.decrementAndGet(), null, "");
    }

    public void log(Severity severity, AnsiColorFormat ansiColorFormat, int depth, Tag tag, String message, Object... args) {
        if (severity == null) severity = Severity.DEBG;
        if (message == null) message = "";
        if (args == null) args = new Object[0];

        String spacing = (" ").repeat(Math.max(0, depth * 2));
        String tagging = tag == null ? "" : "[" + tag + "] ";

        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Throwable) {
                var sw = new StringWriter();
                ((Throwable) args[i]).printStackTrace(new PrintWriter(sw));
                args[i] = sw.toString();
            }
        }

        String str = String.format(Locale.ENGLISH, message, args);
        String timestamp = getTime();

        for (var handler : printHandlers)
            handler.accept(new LogEntry(loggerName, severity, depth, tag, str, timestamp, AnsiColor.generateCode(ansiColorFormat)));

        String display = String.format(Locale.ENGLISH, "%s%s[%s] %s%s", getTime(), spacing, severity, tagging, str);
        console.println(ansiColorFormat, display);

        if (severity == Severity.ERRO && Constants.TREAT_ERRORS_AS_FATAL) {
            throw new FatalTreatedErrorException(str);
        }

        if (this != GLOBAL) {
            if (shouldDispatch(severity, tag)) {
                GLOBAL.log(severity, ansiColorFormat, 0, tag, "[%s] " + message, concat(loggerName, args));
            }
        }
    }

    public void addLogHandler(Consumer<LogEntry> handler) {
        printHandlers.add(handler);
    }

    public String getLoggerName() {
        return loggerName;
    }
}
