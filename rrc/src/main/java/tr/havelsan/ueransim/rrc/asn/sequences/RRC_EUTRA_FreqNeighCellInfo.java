/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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

