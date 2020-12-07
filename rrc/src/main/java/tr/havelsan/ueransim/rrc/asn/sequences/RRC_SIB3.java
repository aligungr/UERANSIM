/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_IntraFreqBlackCellList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_IntraFreqNeighCellList;

public class RRC_SIB3 extends AsnSequence {
    public RRC_IntraFreqNeighCellList intraFreqNeighCellList; // optional
    public RRC_IntraFreqBlackCellList intraFreqBlackCellList; // optional
    public AsnOctetString lateNonCriticalExtension; // optional
}

