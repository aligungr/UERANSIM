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
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_CSI_ResourcePeriodicityAndOffset;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CSI_IM_ResourceId;

public class RRC_CSI_IM_Resource extends AsnSequence {
    public RRC_CSI_IM_ResourceId csi_IM_ResourceId; // mandatory
    public RRC_csi_IM_ResourceElementPattern csi_IM_ResourceElementPattern; // optional
    public RRC_CSI_FrequencyOccupation freqBand; // optional
    public RRC_CSI_ResourcePeriodicityAndOffset periodicityAndOffset; // optional

    public static class RRC_csi_IM_ResourceElementPattern extends AsnChoice {
        public RRC_pattern0 pattern0;
        public RRC_pattern1 pattern1;
    
        public static class RRC_pattern0 extends AsnSequence {
            public RRC_subcarrierLocation_p0 subcarrierLocation_p0; // mandatory
            public AsnInteger symbolLocation_p0; // mandatory, VALUE(0..12)
        
            public static class RRC_subcarrierLocation_p0 extends AsnEnumerated {
                public static final RRC_subcarrierLocation_p0 S0 = new RRC_subcarrierLocation_p0(0);
                public static final RRC_subcarrierLocation_p0 S2 = new RRC_subcarrierLocation_p0(1);
                public static final RRC_subcarrierLocation_p0 S4 = new RRC_subcarrierLocation_p0(2);
                public static final RRC_subcarrierLocation_p0 S6 = new RRC_subcarrierLocation_p0(3);
                public static final RRC_subcarrierLocation_p0 S8 = new RRC_subcarrierLocation_p0(4);
                public static final RRC_subcarrierLocation_p0 S10 = new RRC_subcarrierLocation_p0(5);
            
                private RRC_subcarrierLocation_p0(long value) {
                    super(value);
                }
            }
        }
    
        public static class RRC_pattern1 extends AsnSequence {
            public RRC_subcarrierLocation_p1 subcarrierLocation_p1; // mandatory
            public AsnInteger symbolLocation_p1; // mandatory, VALUE(0..13)
        
            public static class RRC_subcarrierLocation_p1 extends AsnEnumerated {
                public static final RRC_subcarrierLocation_p1 S0 = new RRC_subcarrierLocation_p1(0);
                public static final RRC_subcarrierLocation_p1 S4 = new RRC_subcarrierLocation_p1(1);
                public static final RRC_subcarrierLocation_p1 S8 = new RRC_subcarrierLocation_p1(2);
            
                private RRC_subcarrierLocation_p1(long value) {
                    super(value);
                }
            }
        }
    }
}

