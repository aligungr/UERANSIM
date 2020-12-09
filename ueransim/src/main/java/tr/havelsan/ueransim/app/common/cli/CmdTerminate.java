/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.cli;

public class CmdTerminate extends CmdMessage {
    public final int code;

    public CmdTerminate(int code) {
        this.code = code;
    }
}
