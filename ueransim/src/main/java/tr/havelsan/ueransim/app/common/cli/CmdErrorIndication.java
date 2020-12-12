/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.cli;

public class CmdErrorIndication extends CmdMessage {
    public final String message;

    public CmdErrorIndication(String message) {
        this.message = message;
    }

    public CmdErrorIndication(String format, Object... params) {
        this.message = String.format(format, params);
    }
}
