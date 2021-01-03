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
import tr.havelsan.ueransim.rrc.asn.enums.RRC_PUCCH_MaxCodeRate;

public class RRC_PUCCH_FormatConfig extends AsnSequence {
    public RRC_interslotFrequencyHopping interslotFrequencyHopping; // optional
    public RRC_additionalDMRS additionalDMRS; // optional
    public RRC_PUCCH_MaxCodeRate maxCodeRate; // optional
    public RRC_nrofSlots nrofSlots; // optional
    public RRC_pi2BPSK pi2BPSK; // optional
    public RRC_simultaneousHARQ_ACK_CSI simultaneousHARQ_ACK_CSI; // optional

    public static class RRC_nrofSlots extends AsnEnumerated {
        public static final RRC_nrofSlots N2 = new RRC_nrofSlots(0);
        public static final RRC_nrofSlots N4 = new RRC_nrofSlots(1);
        public static final RRC_nrofSlots N8 = new RRC_nrofSlots(2);
    
        private RRC_nrofSlots(long value) {
            super(value);
        }
    }

    public static class RRC_pi2BPSK extends AsnEnumerated {
        public static final RRC_pi2BPSK ENABLED = new RRC_pi2BPSK(0);
    
        private RRC_pi2BPSK(long value) {
            super(value);
        }
    }

    public static class RRC_simultaneousHARQ_ACK_CSI extends AsnEnumerated {
        public static final RRC_simultaneousHARQ_ACK_CSI TRUE = new RRC_simultaneousHARQ_ACK_CSI(0);
    
        private RRC_simultaneousHARQ_ACK_CSI(long value) {
            super(value);
        }
    }

    public static class RRC_interslotFrequencyHopping extends AsnEnumerated {
        public static final RRC_interslotFrequencyHopping ENABLED = new RRC_interslotFrequencyHopping(0);
    
        private RRC_interslotFrequencyHopping(long value) {
            super(value);
        }
    }

    public static class RRC_additionalDMRS extends AsnEnumerated {
        public static final RRC_additionalDMRS TRUE = new RRC_additionalDMRS(0);
    
        private RRC_additionalDMRS(long value) {
            super(value);
        }
    }
}

