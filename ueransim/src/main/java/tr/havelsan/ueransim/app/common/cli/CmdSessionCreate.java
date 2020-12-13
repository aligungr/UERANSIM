/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.cli;

public class CmdSessionCreate extends CmdMessage {
    public final String ueImsi;

    public CmdSessionCreate(String ueImsi) {
        this.ueImsi = ueImsi;
    }
}
