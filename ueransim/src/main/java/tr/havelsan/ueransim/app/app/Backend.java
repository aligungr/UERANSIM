package tr.havelsan.ueransim.app.app;

import io.javalin.Javalin;
import io.javalin.websocket.WsConnectContext;
import io.javalin.websocket.WsMessageContext;
import org.jetbrains.annotations.NotNull;
import tr.havelsan.ueransim.app.common.sw.*;
import tr.havelsan.ueransim.app.utils.SocketWrapperSerializer;
import tr.havelsan.ueransim.utils.console.Log;
import tr.havelsan.ueransim.utils.console.LogEntry;
import tr.havelsan.ueransim.utils.console.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Backend {

    private static final List<LogEntry> logEntries = new ArrayList<>();
    private static UeRanSim ueRanSim;
    private static final BlockingQueue<String> commandQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        ueRanSim = new UeRanSim();

        var simCtx = ueRanSim.getSimCtx();

        Log.addLogHandler(Backend::addLog);

        for (var ueId : Simulation.allUes(simCtx)) {
            var ue = Simulation.findUe(ueRanSim, ueId);
            if (ue != null) ue.logger.addLogHandler(Backend::addLog);
        }

        for (var gnbId : Simulation.allGnbs(simCtx)) {
            var gnb = Simulation.findGnb(ueRanSim, gnbId);
            if (gnb != null) gnb.logger.addLogHandler(Backend::addLog);
        }

        new Thread(() -> {
            Log.registerLogger(Thread.currentThread(), Logger.GLOBAL);

            while (true) {
                String cmd;
                try {
                    cmd = commandQueue.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                ueRanSim.runTest(cmd);
            }
        }).start();

        Javalin.create().start(7070).ws("/demo", ws -> {
            ws.onConnect(Backend::handleConnect);
            ws.onMessage(Backend::handleMessage);
        });
    }

    private static synchronized void addLog(LogEntry logEntry) {
        logEntries.add(logEntry);
    }

    public static synchronized void handleMessage(@NotNull WsMessageContext ctx) {
        if (!logEntries.isEmpty()) {
            ctx.send(SocketWrapperSerializer.toJson(new SwLog(logEntries)));
        }

        SocketWrapper w = SocketWrapperSerializer.fromJson(ctx.message(), SocketWrapper.class);

        if (!(w instanceof SwHeartbeat)) {
            if (w instanceof SwCommand) {
                SwCommand ew = (SwCommand) w;
                commandQueue.add(ew.commandName);
            }
        }

        logEntries.clear();
    }

    public static synchronized void handleConnect(@NotNull WsConnectContext ctx) {
        ctx.send(SocketWrapperSerializer.toJson(new SwTestCases(ueRanSim.testCaseNames())));
    }
}