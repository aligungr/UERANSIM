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
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_GapConfig;

public class RRC_MeasConfigMN extends AsnSequence {
    public RRC_measuredFrequenciesMN measuredFrequenciesMN; // optional, SIZE(1..32)
    public RRC_SetupRelease_GapConfig measGapConfig; // optional
    public RRC_gapPurpose gapPurpose; // optional
    public RRC_ext1_38 ext1; // optional

    public static class RRC_gapPurpose extends AsnEnumerated {
        public static final RRC_gapPurpose PERUE = new RRC_gapPurpose(0);
        public static final RRC_gapPurpose PERFR1 = new RRC_gapPurpose(1);
    
        private RRC_gapPurpose(long value) {
            super(value);
        }
    }

    // SIZE(1..32)
    public static class RRC_measuredFrequenciesMN extends AsnSequenceOf<RRC_NR_FreqInfo> {
    }

    public static class RRC_ext1_38 extends AsnSequence {
        public RRC_SetupRelease_GapConfig measGapConfigFR2; // optional
    }
}

