/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_Alpha;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_P0_PUSCH_AlphaSetId;

public class RRC_P0_PUSCH_AlphaSet extends AsnSequence {
    public RRC_P0_PUSCH_AlphaSetId p0_PUSCH_AlphaSetId; // mandatory
    public AsnInteger p0; // optional, VALUE(-16..15)
    public RRC_Alpha alpha; // optional
}

