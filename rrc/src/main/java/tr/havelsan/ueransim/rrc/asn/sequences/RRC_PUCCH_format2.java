/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_PUCCH_format2 extends AsnSequence {
    public AsnInteger nrofPRBs; // mandatory, VALUE(1..16)
    public AsnInteger nrofSymbols; // mandatory, VALUE(1..2)
    public AsnInteger startingSymbolIndex; // mandatory, VALUE(0..13)
}

