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

public class RRC_RA_Prioritization extends AsnSequence {
    public RRC_powerRampingStepHighPriority powerRampingStepHighPriority; // mandatory
    public RRC_scalingFactorBI scalingFactorBI; // optional

    public static class RRC_powerRampingStepHighPriority extends AsnEnumerated {
        public static final RRC_powerRampingStepHighPriority DB0 = new RRC_powerRampingStepHighPriority(0);
        public static final RRC_powerRampingStepHighPriority DB2 = new RRC_powerRampingStepHighPriority(1);
        public static final RRC_powerRampingStepHighPriority DB4 = new RRC_powerRampingStepHighPriority(2);
        public static final RRC_powerRampingStepHighPriority DB6 = new RRC_powerRampingStepHighPriority(3);
    
        private RRC_powerRampingStepHighPriority(long value) {
            super(value);
        }
    }

    public static class RRC_scalingFactorBI extends AsnEnumerated {
        public static final RRC_scalingFactorBI ZERO = new RRC_scalingFactorBI(0);
        public static final RRC_scalingFactorBI DOT25 = new RRC_scalingFactorBI(1);
        public static final RRC_scalingFactorBI DOT5 = new RRC_scalingFactorBI(2);
        public static final RRC_scalingFactorBI DOT75 = new RRC_scalingFactorBI(3);
    
        private RRC_scalingFactorBI(long value) {
            super(value);
        }
    }
}

