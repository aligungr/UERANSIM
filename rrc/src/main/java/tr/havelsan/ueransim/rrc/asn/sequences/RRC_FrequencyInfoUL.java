/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueNR;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_AdditionalSpectrumEmission;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_P_Max;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MultiFrequencyBandListNR;

public class RRC_FrequencyInfoUL extends AsnSequence {
    public RRC_MultiFrequencyBandListNR frequencyBandList; // optional
    public RRC_ARFCN_ValueNR absoluteFrequencyPointA; // optional
    public RRC_scs_SpecificCarrierList_4 scs_SpecificCarrierList; // mandatory, SIZE(1..5)
    public RRC_AdditionalSpectrumEmission additionalSpectrumEmission; // optional
    public RRC_P_Max p_Max; // optional
    public RRC_frequencyShift7p5khz_2 frequencyShift7p5khz; // optional

    // SIZE(1..5)
    public static class RRC_scs_SpecificCarrierList_4 extends AsnSequenceOf<RRC_SCS_SpecificCarrier> {
    }

    public static class RRC_frequencyShift7p5khz_2 extends AsnEnumerated {
        public static final RRC_frequencyShift7p5khz_2 TRUE = new RRC_frequencyShift7p5khz_2(0);
    
        private RRC_frequencyShift7p5khz_2(long value) {
            super(value);
        }
    }
}

