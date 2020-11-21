/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.entry;

import tr.havelsan.ueransim.app.app.AppBuilder;
import tr.havelsan.ueransim.app.app.AppConfig;
import tr.havelsan.ueransim.app.common.itms.IwUeTestCommand;
import tr.havelsan.ueransim.app.common.testcmd.TestCmd_InitialRegistration;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.nas.impl.enums.EFollowOnRequest;
import tr.havelsan.ueransim.utils.Utils;

public class AgentApp {

    public static void main(String[] args) {
        BaseApp.main(args);
        new AgentApp().main();
    }

    private void main() {
        var appConfig = new AppConfig();

        var ueransim = new AppBuilder()
                .build();

        var gnbId = ueransim.createGnb(appConfig.createGnbConfig());
        var ueId = ueransim.createUe(appConfig.createUeConfig());

        Utils.sleep(2000);
        ueransim.findUe(ueId).itms.sendMessage(ItmsId.UE_TASK_APP, new IwUeTestCommand(new TestCmd_InitialRegistration(EFollowOnRequest.FOR_PENDING)));
    }
}
