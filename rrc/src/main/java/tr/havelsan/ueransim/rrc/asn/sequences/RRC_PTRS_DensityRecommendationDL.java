/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_PTRS_DensityRecommendationDL extends AsnSequence {
    public AsnInteger frequencyDensity1; // mandatory, VALUE(1..276)
    public AsnInteger frequencyDensity2; // mandatory, VALUE(1..276)
    public AsnInteger timeDensity1; // mandatory, VALUE(0..29)
    public AsnInteger timeDensity2; // mandatory, VALUE(0..29)
    public AsnInteger timeDensity3; // mandatory, VALUE(0..29)
}

