package tr.havelsan.ueransim.app.app.entry;

import io.javalin.Javalin;
import io.javalin.websocket.WsConnectContext;
import io.javalin.websocket.WsMessageContext;
import org.jetbrains.annotations.NotNull;
import tr.havelsan.ueransim.app.app.Simulation;
import tr.havelsan.ueransim.app.app.UeRanSim;
import tr.havelsan.ueransim.app.app.listeners.INodeMessagingListener;
import tr.havelsan.ueransim.app.app.listeners.StepperMessagingListener;
import tr.havelsan.ueransim.app.common.sw.*;
import tr.havelsan.ueransim.app.utils.SocketWrapperSerializer;
import tr.havelsan.ueransim.utils.console.Log;
import tr.havelsan.ueransim.utils.console.LogEntry;
import tr.havelsan.ueransim.utils.console.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class WebApp {

    private static final List<LogEntry> logEntries = new ArrayList<>();
    private static UeRanSim ueRanSim;
    private static final BlockingQueue<String> commandQueue = new LinkedBlockingQueue<>();
    private static final StepperMessagingListener stepperListener = new StepperMessagingListener();

    public static void main(String[] args) {
        var messagingListeners = new ArrayList<INodeMessagingListener>();
        messagingListeners.add(stepperListener);

        ueRanSim = new UeRanSim(messagingListeners);

        var simCtx = ueRanSim.getSimCtx();

        Log.addLogHandler(WebApp::addLog);

        for (var ueId : Simulation.allUes(simCtx)) {
            var ue = Simulation.findUe(ueRanSim, ueId);
            if (ue != null) ue.logger.addLogHandler(WebApp::addLog);
        }

        for (var gnbId : Simulation.allGnbs(simCtx)) {
            var gnb = Simulation.findGnb(ueRanSim, gnbId);
            if (gnb != null) gnb.logger.addLogHandler(WebApp::addLog);
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
            ws.onConnect(WebApp::handleConnect);
            ws.onMessage(WebApp::handleMessage);
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
        stepperListener.onConnect(ctx);
        ctx.send(SocketWrapperSerializer.toJson(new SwTestCases(ueRanSim.testCaseNames())));
    }
}