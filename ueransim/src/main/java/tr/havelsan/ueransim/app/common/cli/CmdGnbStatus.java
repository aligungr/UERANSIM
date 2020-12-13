/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.cli;

public class CmdGnbStatus extends CmdMessage {
    public final int id;

    public CmdGnbStatus(int id) {
        this.id = id;
    }
}
