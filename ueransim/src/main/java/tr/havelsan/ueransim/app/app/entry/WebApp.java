/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.entry;

/*
public class WebApp {

    private static final List<LogEntry> logEntries = new ArrayList<>();
    private static final BlockingQueue<String> commandQueue = new LinkedBlockingQueue<>();
    private static final StepperMessagingListener stepperListener = new StepperMessagingListener();
    private static UeRanSim ueRanSim;

    public static void main(String[] args) {
        BaseApp.main(args);

        ueRanSim = new AppBuilder()
                .addMessagingListener(stepperListener)
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
}*/