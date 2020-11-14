/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.testcmd;

import tr.havelsan.ueransim.nas.impl.enums.EFollowOnRequest;

public class TestCmd_PeriodicRegistration extends TestCmd {
    public final EFollowOnRequest followOn;

    public TestCmd_PeriodicRegistration(EFollowOnRequest followOn) {
        this.followOn = followOn;
    }
}
