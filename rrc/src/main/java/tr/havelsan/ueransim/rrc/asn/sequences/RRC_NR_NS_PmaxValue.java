/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_AdditionalSpectrumEmission;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_P_Max;

public class RRC_NR_NS_PmaxValue extends AsnSequence {
    public RRC_P_Max additionalPmax; // optional
    public RRC_AdditionalSpectrumEmission additionalSpectrumEmission; // mandatory
}

