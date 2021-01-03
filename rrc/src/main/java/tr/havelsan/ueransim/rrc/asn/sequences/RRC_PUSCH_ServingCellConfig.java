/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.*;

public class RRC_PUSCH_ServingCellConfig extends AsnSequence {
    public RRC_SetupRelease_PUSCH_CodeBlockGroupTransmission codeBlockGroupTransmission; // optional
    public RRC_rateMatching rateMatching; // optional
    public RRC_xOverhead_1 xOverhead; // optional
    public RRC_ext1_48 ext1; // optional

    public static class RRC_SetupRelease_PUSCH_CodeBlockGroupTransmission extends AsnChoice {
        public AsnNull release;
        public RRC_PUSCH_CodeBlockGroupTransmission setup;
    }

    public static class RRC_ext1_48 extends AsnSequence {
        public AsnInteger maxMIMO_Layers; // optional, VALUE(1..4)
        public AsnBoolean processingType2Enabled; // optional
    }

    public static class RRC_rateMatching extends AsnEnumerated {
        public static final RRC_rateMatching LIMITEDBUFFERRM = new RRC_rateMatching(0);
    
        private RRC_rateMatching(long value) {
            super(value);
        }
    }

    public static class RRC_xOverhead_1 extends AsnEnumerated {
        public static final RRC_xOverhead_1 XOH6 = new RRC_xOverhead_1(0);
        public static final RRC_xOverhead_1 XOH12 = new RRC_xOverhead_1(1);
        public static final RRC_xOverhead_1 XOH18 = new RRC_xOverhead_1(2);
    
        private RRC_xOverhead_1(long value) {
            super(value);
        }
    }
}

