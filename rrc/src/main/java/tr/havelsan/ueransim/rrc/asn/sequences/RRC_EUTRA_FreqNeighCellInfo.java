/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_EUTRA_Q_OffsetRange;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_EUTRA_PhysCellId;

public class RRC_EUTRA_FreqNeighCellInfo extends AsnSequence {
    public RRC_EUTRA_PhysCellId physCellId; // mandatory
    public RRC_EUTRA_Q_OffsetRange dummy; // mandatory
    public AsnInteger q_RxLevMinOffsetCell; // optional, VALUE(1..8)
    public AsnInteger q_QualMinOffsetCell; // optional, VALUE(1..8)
}

