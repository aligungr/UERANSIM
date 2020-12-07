/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_EUTRA_NS_PmaxValue extends AsnSequence {
    public AsnInteger additionalPmax; // optional, VALUE(-30..33)
    public AsnInteger additionalSpectrumEmission; // optional, VALUE(1..288)
}

