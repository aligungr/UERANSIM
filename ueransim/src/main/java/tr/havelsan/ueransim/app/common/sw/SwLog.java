/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.sw;

import tr.havelsan.ueransim.utils.console.LogEntry;

import java.util.List;

public class SwLog extends SocketWrapper {
    public final List<LogEntry> data;

    public SwLog(List<LogEntry> data) {
        this.data = data;
    }
}
