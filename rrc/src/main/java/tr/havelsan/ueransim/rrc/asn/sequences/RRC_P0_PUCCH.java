/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_P0_PUCCH_Id;

public class RRC_P0_PUCCH extends AsnSequence {
    public RRC_P0_PUCCH_Id p0_PUCCH_Id; // mandatory
    public AsnInteger p0_PUCCH_Value; // mandatory, VALUE(-16..15)
}

