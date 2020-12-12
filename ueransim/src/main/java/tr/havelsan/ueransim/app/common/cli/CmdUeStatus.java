/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.cli;

public class CmdUeStatus extends CmdMessage {
    public final String imsi;

    public CmdUeStatus(String imsi) {
        this.imsi = imsi;
    }
}
