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

public class RRC_PUCCH_format2 extends AsnSequence {
    public AsnInteger nrofPRBs; // mandatory, VALUE(1..16)
    public AsnInteger nrofSymbols; // mandatory, VALUE(1..2)
    public AsnInteger startingSymbolIndex; // mandatory, VALUE(0..13)
}

