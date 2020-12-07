/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SSB_Index;

public class RRC_CFRA_SSB_Resource extends AsnSequence {
    public RRC_SSB_Index ssb; // mandatory
    public AsnInteger ra_PreambleIndex; // mandatory, VALUE(0..63)
}

