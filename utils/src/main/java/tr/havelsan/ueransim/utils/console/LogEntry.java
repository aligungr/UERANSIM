/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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
