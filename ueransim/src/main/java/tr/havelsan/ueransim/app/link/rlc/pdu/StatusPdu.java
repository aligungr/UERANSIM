/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.link.rlc.pdu;

import tr.havelsan.ueransim.app.link.rlc.utils.NackBlock;

import java.util.ArrayList;
import java.util.List;

public class StatusPdu {
    public int ackSn;
    public List<NackBlock> nackBlocks = new ArrayList<>();
}
