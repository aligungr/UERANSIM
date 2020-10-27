package tr.havelsan.ueransim.app.common.sw;

import tr.havelsan.ueransim.utils.console.LogEntry;

import java.util.List;

public class SwLog extends SocketWrapper {
    public final List<LogEntry> data;

    public SwLog(List<LogEntry> data) {
        this.data = data;
    }
}
