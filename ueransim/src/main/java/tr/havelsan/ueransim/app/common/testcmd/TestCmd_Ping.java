/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.testcmd;

public class TestCmd_Ping extends TestCmd {
    public final String address;
    public final int count;
    public final int timeoutSec;

    public TestCmd_Ping(String address, int count, int timeoutSec) {
        this.address = address;
        this.count = count;
        this.timeoutSec = timeoutSec;
    }
}
