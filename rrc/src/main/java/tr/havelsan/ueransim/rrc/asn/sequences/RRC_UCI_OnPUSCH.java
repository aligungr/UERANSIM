/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;

public class RRC_UCI_OnPUSCH extends AsnSequence {
    public RRC_betaOffsets betaOffsets; // optional
    public RRC_scaling scaling; // mandatory

    public static class RRC_betaOffsets extends AsnChoice {
        public RRC_dynamic_1 dynamic; // SIZE(4)
        public RRC_BetaOffsets semiStatic;
    
        // SIZE(4)
        public static class RRC_dynamic_1 extends AsnSequenceOf<RRC_BetaOffsets> {
        }
    }

    public static class RRC_scaling extends AsnEnumerated {
        public static final RRC_scaling F0P5 = new RRC_scaling(0);
        public static final RRC_scaling F0P65 = new RRC_scaling(1);
        public static final RRC_scaling F0P8 = new RRC_scaling(2);
        public static final RRC_scaling F1 = new RRC_scaling(3);
    
        private RRC_scaling(long value) {
            super(value);
        }
    }
}

