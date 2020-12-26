/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.link.rlc.pdu;

import tr.havelsan.ueransim.app.link.rlc.encoding.StatusEncoder;
import tr.havelsan.ueransim.app.link.rlc.utils.NackBlock;
import tr.havelsan.ueransim.utils.BitOutputStream;

import java.util.ArrayList;
import java.util.List;

public class StatusPdu {
    public int ackSn;
    public List<NackBlock> nackBlocks = new ArrayList<>();

    public int calculatedSize(boolean isShortSn) {
        // TODO optimize. this is waay slow and just for POC
        var stream = new BitOutputStream();
        StatusEncoder.encode(stream, this, isShortSn);
        return stream.toOctetString().length;
    }
}
