/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.entry;

import tr.havelsan.ueransim.app.app.AppBuilder;
import tr.havelsan.ueransim.app.app.ProcedureTester;

public class AgentApp {

    public static void main(String[] args) {
        BaseApp.main(args);
        new AgentApp().main();
    }

    private void main() {
        var procTester = new ProcedureTester();

        var ueransim = new AppBuilder()
                .addConnectionListener(procTester)
                .addMessagingListener(procTester)
                .build();

        procTester.start(ueransim, 3);
    }
}
