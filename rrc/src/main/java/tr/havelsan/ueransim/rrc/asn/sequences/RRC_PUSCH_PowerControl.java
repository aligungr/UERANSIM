/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_Alpha;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PUSCH_PathlossReferenceRS_Id;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SRI_PUSCH_PowerControlId;

public class RRC_PUSCH_PowerControl extends AsnSequence {
    public RRC_tpc_Accumulation_2 tpc_Accumulation; // optional
    public RRC_Alpha msg3_Alpha; // optional
    public AsnInteger p0_NominalWithoutGrant; // optional, VALUE(-202..24)
    public RRC_p0_AlphaSets p0_AlphaSets; // optional, SIZE(1..30)
    public RRC_pathlossReferenceRSToAddModList pathlossReferenceRSToAddModList; // optional, SIZE(1..4)
    public RRC_pathlossReferenceRSToReleaseList pathlossReferenceRSToReleaseList; // optional, SIZE(1..4)
    public RRC_twoPUSCH_PC_AdjustmentStates twoPUSCH_PC_AdjustmentStates; // optional
    public RRC_deltaMCS deltaMCS; // optional
    public RRC_sri_PUSCH_MappingToAddModList sri_PUSCH_MappingToAddModList; // optional, SIZE(1..16)
    public RRC_sri_PUSCH_MappingToReleaseList sri_PUSCH_MappingToReleaseList; // optional, SIZE(1..16)

    // SIZE(1..16)
    public static class RRC_sri_PUSCH_MappingToAddModList extends AsnSequenceOf<RRC_SRI_PUSCH_PowerControl> {
    }

    // SIZE(1..4)
    public static class RRC_pathlossReferenceRSToAddModList extends AsnSequenceOf<RRC_PUSCH_PathlossReferenceRS> {
    }

    // SIZE(1..16)
    public static class RRC_sri_PUSCH_MappingToReleaseList extends AsnSequenceOf<RRC_SRI_PUSCH_PowerControlId> {
    }

    public static class RRC_twoPUSCH_PC_AdjustmentStates extends AsnEnumerated {
        public static final RRC_twoPUSCH_PC_AdjustmentStates TWOSTATES = new RRC_twoPUSCH_PC_AdjustmentStates(0);
    
        private RRC_twoPUSCH_PC_AdjustmentStates(long value) {
            super(value);
        }
    }

    public static class RRC_deltaMCS extends AsnEnumerated {
        public static final RRC_deltaMCS ENABLED = new RRC_deltaMCS(0);
    
        private RRC_deltaMCS(long value) {
            super(value);
        }
    }

    // SIZE(1..4)
    public static class RRC_pathlossReferenceRSToReleaseList extends AsnSequenceOf<RRC_PUSCH_PathlossReferenceRS_Id> {
    }

    public static class RRC_tpc_Accumulation_2 extends AsnEnumerated {
        public static final RRC_tpc_Accumulation_2 DISABLED = new RRC_tpc_Accumulation_2(0);
    
        private RRC_tpc_Accumulation_2(long value) {
            super(value);
        }
    }

    // SIZE(1..30)
    public static class RRC_p0_AlphaSets extends AsnSequenceOf<RRC_P0_PUSCH_AlphaSet> {
    }
}

