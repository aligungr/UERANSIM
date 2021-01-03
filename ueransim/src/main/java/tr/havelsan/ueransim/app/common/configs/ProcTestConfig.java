/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.common.configs;

import tr.havelsan.ueransim.nas.impl.enums.EFollowOnRequest;

public class ProcTestConfig {
    public final int numberOfUe;
    public final EFollowOnRequest forPending;
    public final boolean isSwitchOff;
    public final String pingAddress;
    public final int pingCount;
    public final int pingTimeoutSec;

    public ProcTestConfig(int numberOfUe, EFollowOnRequest forPending, boolean isSwitchOff, String pingAddress, int pingCount, int pingTimeoutSec) {
        this.numberOfUe = numberOfUe;
        this.forPending = forPending;
        this.isSwitchOff = isSwitchOff;
        this.pingAddress = pingAddress;
        this.pingCount = pingCount;
        this.pingTimeoutSec = pingTimeoutSec;
    }
}
