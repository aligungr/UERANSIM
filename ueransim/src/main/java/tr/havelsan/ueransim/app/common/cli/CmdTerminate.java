/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.cli;

public class CmdTerminate extends CmdMessage {
    public final int code;
    public final String finalOutput;

    public CmdTerminate(int code, String finalOutput, Object... args) {
        this.code = code;
        this.finalOutput = String.format(finalOutput, args);
    }
}
