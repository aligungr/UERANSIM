/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.*;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_Alpha;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_NZP_CSI_RS_ResourceId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SRS_ResourceId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SRS_ResourceSetId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SSB_Index;

public class RRC_SRS_ResourceSet extends AsnSequence {
    public RRC_SRS_ResourceSetId srs_ResourceSetId; // mandatory
    public RRC_srs_ResourceIdList srs_ResourceIdList; // optional, SIZE(1..16)
    public RRC_resourceType_3 resourceType; // mandatory
    public RRC_usage usage; // mandatory
    public RRC_Alpha alpha; // optional
    public AsnInteger p0; // optional, VALUE(-202..24)
    public RRC_pathlossReferenceRS pathlossReferenceRS; // optional
    public RRC_srs_PowerControlAdjustmentStates srs_PowerControlAdjustmentStates; // optional

    // SIZE(1..16)
    public static class RRC_srs_ResourceIdList extends AsnSequenceOf<RRC_SRS_ResourceId> {
    }

    public static class RRC_resourceType_3 extends AsnChoice {
        public RRC_aperiodic_1 aperiodic;
        public RRC_semi_persistent_1 semi_persistent;
        public RRC_periodic_2 periodic;
    
        public static class RRC_semi_persistent_1 extends AsnSequence {
            public RRC_NZP_CSI_RS_ResourceId associatedCSI_RS; // optional
        }
    
        public static class RRC_periodic_2 extends AsnSequence {
            public RRC_NZP_CSI_RS_ResourceId associatedCSI_RS; // optional
        }
    
        public static class RRC_aperiodic_1 extends AsnSequence {
            public AsnInteger aperiodicSRS_ResourceTrigger; // mandatory, VALUE(1..3)
            public RRC_NZP_CSI_RS_ResourceId csi_RS; // optional
            public AsnInteger slotOffset; // optional, VALUE(1..32)
            public RRC_ext1_51 ext1; // optional
        
            public static class RRC_ext1_51 extends AsnSequence {
                public RRC_aperiodicSRS_ResourceTriggerList_v1530 aperiodicSRS_ResourceTriggerList_v1530; // optional, SIZE(1..2)
            
                // SIZE(1..2)
                public static class RRC_aperiodicSRS_ResourceTriggerList_v1530 extends AsnSequenceOf<AsnInteger> {
                }
            }
        }
    }

    public static class RRC_usage extends AsnEnumerated {
        public static final RRC_usage BEAMMANAGEMENT = new RRC_usage(0);
        public static final RRC_usage CODEBOOK = new RRC_usage(1);
        public static final RRC_usage NONCODEBOOK = new RRC_usage(2);
        public static final RRC_usage ANTENNASWITCHING = new RRC_usage(3);
    
        private RRC_usage(long value) {
            super(value);
        }
    }

    public static class RRC_pathlossReferenceRS extends AsnChoice {
        public RRC_SSB_Index ssb_Index;
        public RRC_NZP_CSI_RS_ResourceId csi_RS_Index;
    }

    public static class RRC_srs_PowerControlAdjustmentStates extends AsnEnumerated {
        public static final RRC_srs_PowerControlAdjustmentStates SAMEASFCI2 = new RRC_srs_PowerControlAdjustmentStates(0);
        public static final RRC_srs_PowerControlAdjustmentStates SEPARATECLOSEDLOOP = new RRC_srs_PowerControlAdjustmentStates(1);
    
        private RRC_srs_PowerControlAdjustmentStates(long value) {
            super(value);
        }
    }
}

