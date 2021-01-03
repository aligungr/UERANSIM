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
import tr.havelsan.ueransim.rrc.asn.integers.*;

public class RRC_PUCCH_SpatialRelationInfo extends AsnSequence {
    public RRC_PUCCH_SpatialRelationInfoId pucch_SpatialRelationInfoId; // mandatory
    public RRC_ServCellIndex servingCellId; // optional
    public RRC_referenceSignal_2 referenceSignal; // mandatory
    public RRC_PUCCH_PathlossReferenceRS_Id pucch_PathlossReferenceRS_Id; // mandatory
    public RRC_P0_PUCCH_Id p0_PUCCH_Id; // mandatory
    public RRC_closedLoopIndex closedLoopIndex; // mandatory

    public static class RRC_referenceSignal_2 extends AsnChoice {
        public RRC_SSB_Index ssb_Index;
        public RRC_NZP_CSI_RS_ResourceId csi_RS_Index;
        public RRC_srs_1 srs;
    
        public static class RRC_srs_1 extends AsnSequence {
            public RRC_SRS_ResourceId resource; // mandatory
            public RRC_BWP_Id uplinkBWP; // mandatory
        }
    }

    public static class RRC_closedLoopIndex extends AsnEnumerated {
        public static final RRC_closedLoopIndex I0 = new RRC_closedLoopIndex(0);
        public static final RRC_closedLoopIndex I1 = new RRC_closedLoopIndex(1);
    
        private RRC_closedLoopIndex(long value) {
            super(value);
        }
    }
}

