/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnBitString;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_ReestablishmentCause;

public class RRC_RRCReestablishmentRequest_IEs extends AsnSequence {
    public RRC_ReestabUE_Identity ue_Identity; // mandatory
    public RRC_ReestablishmentCause reestablishmentCause; // mandatory
    public AsnBitString spare; // mandatory, SIZE(1)
}

