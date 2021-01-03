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
import tr.havelsan.ueransim.rrc.asn.integers.*;

public class RRC_CSI_ResourceConfig extends AsnSequence {
    public RRC_CSI_ResourceConfigId csi_ResourceConfigId; // mandatory
    public RRC_csi_RS_ResourceSetList csi_RS_ResourceSetList; // mandatory
    public RRC_BWP_Id bwp_Id; // mandatory
    public RRC_resourceType_2 resourceType; // mandatory

    public static class RRC_resourceType_2 extends AsnEnumerated {
        public static final RRC_resourceType_2 APERIODIC = new RRC_resourceType_2(0);
        public static final RRC_resourceType_2 SEMIPERSISTENT = new RRC_resourceType_2(1);
        public static final RRC_resourceType_2 PERIODIC = new RRC_resourceType_2(2);
    
        private RRC_resourceType_2(long value) {
            super(value);
        }
    }

    public static class RRC_csi_RS_ResourceSetList extends AsnChoice {
        public RRC_nzp_CSI_RS_SSB nzp_CSI_RS_SSB;
        public RRC_csi_IM_ResourceSetList csi_IM_ResourceSetList; // SIZE(1..16)
    
        // SIZE(1..16)
        public static class RRC_csi_IM_ResourceSetList extends AsnSequenceOf<RRC_CSI_IM_ResourceSetId> {
        }
    
        public static class RRC_nzp_CSI_RS_SSB extends AsnSequence {
            public RRC_nzp_CSI_RS_ResourceSetList nzp_CSI_RS_ResourceSetList; // optional, SIZE(1..16)
            public RRC_csi_SSB_ResourceSetList csi_SSB_ResourceSetList; // optional, SIZE(1)
        
            // SIZE(1..16)
            public static class RRC_nzp_CSI_RS_ResourceSetList extends AsnSequenceOf<RRC_NZP_CSI_RS_ResourceSetId> {
            }
        
            // SIZE(1)
            public static class RRC_csi_SSB_ResourceSetList extends AsnSequenceOf<RRC_CSI_SSB_ResourceSetId> {
            }
        }
    }
}

