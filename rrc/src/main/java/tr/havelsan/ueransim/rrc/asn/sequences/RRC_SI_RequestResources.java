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

public class RRC_SI_RequestResources extends AsnSequence {
    public AsnInteger ra_PreambleStartIndex; // mandatory, VALUE(0..63)
    public AsnInteger ra_AssociationPeriodIndex; // optional, VALUE(0..15)
    public AsnInteger ra_ssb_OccasionMaskIndex; // optional, VALUE(0..15)
}

