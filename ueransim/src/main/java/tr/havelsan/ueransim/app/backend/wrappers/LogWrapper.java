package tr.havelsan.ueransim.app.backend.wrappers;

import tr.havelsan.ueransim.utils.console.LogEntry;

import java.util.List;

public class LogWrapper extends Wrapper {
    public final List<LogEntry> data;

    public LogWrapper(List<LogEntry> data) {
        this.data = data;
    }
}
