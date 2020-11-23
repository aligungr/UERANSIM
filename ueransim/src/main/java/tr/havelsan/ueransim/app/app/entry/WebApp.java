/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.entry;

import io.javalin.Javalin;
import io.javalin.websocket.WsConnectContext;
import io.javalin.websocket.WsMessageContext;
import tr.havelsan.ueransim.app.app.AppBuilder;
import tr.havelsan.ueransim.app.app.UeRanSim;
import tr.havelsan.ueransim.app.app.monitor.StepperMonitor;
import tr.havelsan.ueransim.app.common.sw.*;
import tr.havelsan.ueransim.app.utils.SocketWrapperSerializer;
import tr.havelsan.ueransim.utils.console.Log;
import tr.havelsan.ueransim.utils.console.LogEntry;
import tr.havelsan.ueransim.utils.console.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/*
public class WebApp {

    private static final List<LogEntry> logEntries = new ArrayList<>();
    private static final BlockingQueue<String> commandQueue = new LinkedBlockingQueue<>();
    private static final StepperMonitor stepperListener = new StepperMonitor();
    private static UeRanSim ueRanSim;

    public static void main(String[] args) {
        BaseApp.main(args);

        ueRanSim = new AppBuilder()
                .addMonitor(stepperListener)
                .build();

        Log.addLogHandler(WebApp::addLog);

        for (var ueId : ueRanSim.allUes()) {
            var ue = ueRanSim.findUe(ueId);
            if (ue != null) ue.logger.addLogHandler(WebApp::addLog);
        }

        for (var gnbId : ueRanSim.allGnbs()) {
            var gnb = ueRanSim.findGnb(gnbId);
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

        Javalin.create().start(1071).ws("/web-interface", ws -> {
            ws.onConnect(WebApp::handleConnect);
            ws.onMessage(WebApp::handleMessage);
        });
    }

    private static synchronized void addLog(LogEntry logEntry) {
        logEntries.add(logEntry);
    }

    public static synchronized void handleMessage(WsMessageContext ctx) {
        if (!logEntries.isEmpty()) {
            ctx.send(SocketWrapperSerializer.toJson(new SwLog(logEntries)));
        }

        var w = SocketWrapperSerializer.fromJson(ctx.message(), SocketWrapper.class);

        if (!(w instanceof SwHeartbeat)) {
            if (w instanceof SwCommand) {
                SwCommand ew = (SwCommand) w;
                commandQueue.add(ew.commandName);
            }
        }

        logEntries.clear();
    }

    public static synchronized void handleConnect(WsConnectContext ctx) {
        stepperListener.onConnect(ctx);
        ctx.send(SocketWrapperSerializer.toJson(new SwTestCases(ueRanSim.testCaseNames())));
    }
}

 */
