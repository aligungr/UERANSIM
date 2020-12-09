/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.entry;

import tr.havelsan.ueransim.app.app.AppBuilder;
import tr.havelsan.ueransim.app.app.AppConfig;
import tr.havelsan.ueransim.app.app.UeRanSim;
import tr.havelsan.ueransim.app.app.cli.CliTask;
import tr.havelsan.ueransim.app.app.cli.DispatchMonitor;

public class AgentApp {

    private UeRanSim ueransim;
    private AppConfig appConfig;

    private CliTask cliTask;
    private DispatchMonitor dispatchMonitor;

    public static void main(String[] args) {
        BaseApp.main(args, true);
        new AgentApp().main();
    }

    private void main() {
        appConfig = new AppConfig();

        dispatchMonitor = new DispatchMonitor(); // started by ueransim instance

        ueransim = new AppBuilder()
                .addMonitor(dispatchMonitor)
                .build();

        cliTask = new CliTask(appConfig, ueransim);
        cliTask.start();
    }
}
