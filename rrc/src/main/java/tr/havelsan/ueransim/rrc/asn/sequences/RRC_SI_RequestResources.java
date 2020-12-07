/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_SI_RequestResources extends AsnSequence {
    public AsnInteger ra_PreambleStartIndex; // mandatory, VALUE(0..63)
    public AsnInteger ra_AssociationPeriodIndex; // optional, VALUE(0..15)
    public AsnInteger ra_ssb_OccasionMaskIndex; // optional, VALUE(0..15)
}

