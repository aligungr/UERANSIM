/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.link.rlc.pdu;

import tr.havelsan.ueransim.app.link.rlc.utils.ESegmentInfo;
import tr.havelsan.ueransim.utils.octets.OctetString;

public abstract class RxPdu {
    public ESegmentInfo si;
    public int so;
    public int sn;
    public OctetString data;

    // Indicates whether this SDU is delivered.
    //  If the SDU has fragmentation, ALL Pdu which has the same underlying SDU must be
    //  marked as _isDelivered=true at the reassembling and delivering operation.
    public boolean _isProcessed;
}
