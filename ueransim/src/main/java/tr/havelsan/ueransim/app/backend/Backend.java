package tr.havelsan.ueransim.app.backend;

import io.javalin.Javalin;
import io.javalin.websocket.WsConnectContext;
import io.javalin.websocket.WsMessageContext;
import org.jetbrains.annotations.NotNull;
import tr.havelsan.ueransim.app.Simulation;
import tr.havelsan.ueransim.app.UeRanSim;
import tr.havelsan.ueransim.utils.Json;
import tr.havelsan.ueransim.utils.console.Log;
import tr.havelsan.ueransim.utils.console.LogEntry;

import java.util.ArrayList;
import java.util.List;

public class Backend {

    private static final List<LogEntry> logEntries = new ArrayList<>();
    private static UeRanSim ueRanSim;

    public static void main(String[] args) {
        ueRanSim = new UeRanSim();

        var simCtx = ueRanSim.getSimCtx();

        Log.addLogHandler(Backend::addLog);

        for (var ueId : Simulation.allUes(simCtx)) {
            var ue = Simulation.findUe(simCtx, ueId);
            if (ue != null) ue.logger.addLogHandler(Backend::addLog);
        }

        for (var gnbId : Simulation.allGnbs(simCtx)) {
            var gnb = Simulation.findGnb(simCtx, gnbId);
            if (gnb != null) gnb.logger.addLogHandler(Backend::addLog);
        }

        Javalin.create().start(7070).ws("/demo", ws -> {
            ws.onConnect(Backend::handleConnect);
            ws.onMessage(Backend::handleMessage);
        });
    }

    private static synchronized void addLog(LogEntry logEntry) {
        logEntries.add(logEntry);
    }

    public static synchronized void handleMessage(@NotNull WsMessageContext ctx) {
        for (var s : logEntries) {
            ctx.send(new Wrapper("log", s));
        }

        String s = Json.fromJson(ctx.message(), String.class);

        if (s.length() > 0 && !s.equals("dummy"))
            ueRanSim.runTest(s);

        logEntries.clear();
    }

    public static synchronized void handleConnect(@NotNull WsConnectContext ctx) {
        ctx.send(new Wrapper("possibleEvents", ueRanSim.testCaseNames()));
    }

    public static class Wrapper {
        public final String type;
        public final Object message;

        public Wrapper(String type, Object message) {
            this.type = type;
            this.message = message;
        }

        @Override
        public String toString() {
            return Json.toJson(this);
        }
    }
}