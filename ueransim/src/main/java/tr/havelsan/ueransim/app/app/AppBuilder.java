/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app;

import tr.havelsan.ueransim.app.app.monitor.LoadTestMonitor;
import tr.havelsan.ueransim.app.app.monitor.MonitorTask;
import tr.havelsan.ueransim.app.utils.ConfigUtils;
import tr.havelsan.ueransim.app.utils.Native;
import tr.havelsan.ueransim.utils.FileUtils;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.BaseConsole;
import tr.havelsan.ueransim.utils.console.Log;
import tr.havelsan.ueransim.utils.console.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class AppBuilder {

    private static final AtomicBoolean isBuilt = new AtomicBoolean(false);
    private final List<MonitorTask> monitorTasks;

    public AppBuilder() {
        this.monitorTasks = new ArrayList<>();
    }

    //======================================================================================================
    //                                          MUTATORS
    //======================================================================================================

    public AppBuilder addMonitor(MonitorTask monitor) {
        this.monitorTasks.add(monitor);
        return this;
    }

    //======================================================================================================
    //                                          BUILDER
    //======================================================================================================

    public UeRanSim build() {
        if (isBuilt.getAndSet(true))
            throw new RuntimeException("already built");

        FileUtils.createDir("logs");

        ConfigUtils.loggingToFile(Logger.GLOBAL, Logger.GLOBAL.getLoggerName(), true);
        Log.registerLogger(Thread.currentThread(), Logger.GLOBAL);

        var loadTestConsole = createLoadTestingConsole();

        monitorTasks.add(new LoadTestMonitor() {
            protected void onIntervalResult(String id, String display, boolean isSuccess, String nodeName, long deltaMs) {
                String icon = isSuccess ? "\u2714" : "\u2718";
                loadTestConsole.println(null, "%s %s [%s] [%d ms]", icon, display, nodeName, deltaMs);
            }
        });

        Log.info(Tag.SYS, "UERANSIM agent has been started.");

        if (!Native.isRoot()) {
            Log.warning(Tag.SYS, "Automatic TUN configuration will not work. Please run UERANSIM with 'sudo'.");
        }

        return new UeRanSim(monitorTasks);
    }

    private BaseConsole createLoadTestingConsole() {
        var loadTestConsole = new BaseConsole();
        loadTestConsole.setStandardPrintEnabled(false);
        loadTestConsole.addPrintHandler(str -> FileUtils.appendToFile("logs/loadtest.log", str));
        loadTestConsole.printDiv();
        return loadTestConsole;
    }
}
