/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.cli;

public class CmdUePing extends CmdMessage {
    public String ueImsi;
    public String address;
    public int count;
    public int timeoutSec;
}
