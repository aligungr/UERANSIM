/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.testcmd;

public class TestCmd_Sleep extends TestCmd {

    public final int duration;

    public TestCmd_Sleep(int duration) {
        this.duration = duration;
    }
}
