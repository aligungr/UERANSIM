/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.utils.console;

import tr.havelsan.ueransim.utils.Severity;
import tr.havelsan.ueransim.utils.Tag;

public class LogEntry {
    public final String loggerName;
    public final Severity severity;
    public final Tag tag;
    public final String message;
    public final String timestamp;
    public final String color;

    public LogEntry(String loggerName, Severity severity, Tag tag, String message, String timestamp, String color) {
        this.loggerName = loggerName;
        this.severity = severity;
        this.tag = tag;
        this.message = message;
        this.timestamp = timestamp;
        this.color = color;
    }
}
