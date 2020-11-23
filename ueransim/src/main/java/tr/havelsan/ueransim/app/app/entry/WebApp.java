/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.entry;

import io.javalin.Javalin;
import io.javalin.websocket.WsContext;
import tr.havelsan.ueransim.app.app.AppBuilder;
import tr.havelsan.ueransim.app.app.AppConfig;
import tr.havelsan.ueransim.app.app.UeRanSim;
import tr.havelsan.ueransim.app.app.monitor.LoadTestMonitor;
import tr.havelsan.ueransim.app.app.monitor.StepperMonitor;
import tr.havelsan.ueransim.app.app.tester.ProcedureTester;
import tr.havelsan.ueransim.app.common.sw.*;
import tr.havelsan.ueransim.app.utils.ConfigUtils;
import tr.havelsan.ueransim.app.utils.SocketWrapperSerializer;
import tr.havelsan.ueransim.itms.nts.NtsTask;
import tr.havelsan.ueransim.utils.Fun;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.console.Log;
import tr.havelsan.ueransim.utils.console.LogEntry;

import java.util.ArrayList;
import java.util.function.Consumer;

// TODO: What if multiple WS clients connect us?
public class WebApp {

    private ReceiverTask receiverTask;
    private SenderTask senderTask;
    private LogTask logTask;

    private ProcedureTester procTester;
    private UeRanSim ueransim;

    public WebApp() {
        Fun initTasks = () -> {
            receiverTask = new ReceiverTask();
            senderTask = new SenderTask();
            logTask = new LogTask();

            receiverTask.senderTask = senderTask;
            senderTask.receiverTask = receiverTask;
            logTask.senderTask = senderTask;

            senderTask.start();
            receiverTask.start();
            logTask.start();
        };

        Fun initLogger = () -> {
            Consumer<LogEntry> logHandler = logTask::push;

            Log.addLogHandler(logHandler);

            for (var ueId : ueransim.allUes()) {
                var ue = ueransim.findUe(ueId);
                if (ue != null) ue.logger.addLogHandler(logHandler);
            }

            for (var gnbId : ueransim.allGnbs()) {
                var gnb = ueransim.findGnb(gnbId);
                if (gnb != null) gnb.logger.addLogHandler(logHandler);
            }
        };

        Fun initJavalin = () -> Javalin.create().start(1071).ws("/web-interface", ws -> {
            ws.onConnect(wsc -> senderTask.push(new ConnectionMarker(wsc)));
            ws.onMessage(wsc -> receiverTask.push(SocketWrapperSerializer.fromJson(wsc.message(), SocketWrapper.class)));
        });

        Fun buildApp = () -> {
            var stepperMonitor = new StepperMonitor(senderTask::push);
            var loadTestNotifierMonitor = new LoadTestNotifierMonitor(senderTask);
            var appConfig = new AppConfig();

            procTester = new ProcedureTester(appConfig);
            ueransim = new AppBuilder()
                    .addMonitor(stepperMonitor)
                    .addMonitor(procTester)
                    .addMonitor(loadTestNotifierMonitor)
                    .build();
        };

        Fun initApp = () -> {
            var procTestConfig = ConfigUtils.createProcTestConfig();

            receiverTask.procTester = procTester;
            procTester.init(ueransim, procTestConfig, initLogger::run);
        };

        initTasks.run();
        buildApp.run();
        initJavalin.run();
        initApp.run();
    }

    public static void main(String[] args) {
        BaseApp.main(args);
        new WebApp();
    }

    private static class ConnectionMarker {
        private final WsContext ws;

        public ConnectionMarker(WsContext ws) {
            this.ws = ws;
        }
    }

    private static class ReceiverTask extends NtsTask {
        public SenderTask senderTask;
        public ProcedureTester procTester;

        @Override
        public void main() {
            while (true) {
                var msg = take();
                if (msg instanceof SwCommand) {
                    procTester.startTestCase(((SwCommand) msg).commandName);
                }
            }
        }
    }

    private static class SenderTask extends NtsTask {
        public ReceiverTask receiverTask;
        private WsContext ws;

        @Override
        public void main() {
            while (true) {
                var msg = take();
                if (msg instanceof ConnectionMarker) {
                    ws = ((ConnectionMarker) msg).ws;
                    push(new SwTestCases(ProcedureTester.testCases()));
                    push(new SwIntervalMetadata(LoadTestMonitor.IntervalMetadata.INSTANCE));
                } else if (msg instanceof SocketWrapper) {
                    if (ws != null) {
                        ws.send(SocketWrapperSerializer.toJson(msg));
                    } else {
                        push(msg);
                        Utils.sleep(10);
                    }
                }
            }
        }
    }

    private static class LogTask extends NtsTask {
        public SenderTask senderTask;

        @Override
        public void main() {
            // In this task, we use buffering instead of sending the logs one by one.
            while (true) {
                Utils.sleep(150);

                if (senderTask == null)
                    continue;

                var list = new ArrayList<LogEntry>();

                LogEntry entry;
                do {
                    entry = (LogEntry) poll();
                    if (entry != null)
                        list.add(entry);
                } while (entry != null);

                if (!list.isEmpty()) {
                    senderTask.push(new SwLog(list));
                }
            }
        }
    }

    private static class LoadTestNotifierMonitor extends LoadTestMonitor {
        public final SenderTask senderTask;

        public LoadTestNotifierMonitor(SenderTask senderTask) {
            this.senderTask = senderTask;
        }

        @Override
        protected void onIntervalResult(String id, String display, boolean isSuccess, String nodeName, long deltaMs) {
            senderTask.push(new SwIntervalResult(id, isSuccess, nodeName, deltaMs));
        }
    }
}
