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
import tr.havelsan.ueransim.rrc.asn.choices.RRC_PRACH_ResourceDedicatedBFR;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RSRP_Range;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SearchSpaceId;

public class RRC_BeamFailureRecoveryConfig extends AsnSequence {
    public AsnInteger rootSequenceIndex_BFR; // optional, VALUE(0..137)
    public RRC_RACH_ConfigGeneric rach_ConfigBFR; // optional
    public RRC_RSRP_Range rsrp_ThresholdSSB; // optional
    public RRC_candidateBeamRSList candidateBeamRSList; // optional, SIZE(1..16)
    public RRC_ssb_perRACH_Occasion_3 ssb_perRACH_Occasion; // optional
    public AsnInteger ra_ssb_OccasionMaskIndex; // optional, VALUE(0..15)
    public RRC_SearchSpaceId recoverySearchSpaceId; // optional
    public RRC_RA_Prioritization ra_Prioritization; // optional
    public RRC_beamFailureRecoveryTimer beamFailureRecoveryTimer; // optional
    public RRC_ext1_28 ext1; // optional

    public static class RRC_ext1_28 extends AsnSequence {
        public RRC_SubcarrierSpacing msg1_SubcarrierSpacing_v1530; // optional
    }

    public static class RRC_beamFailureRecoveryTimer extends AsnEnumerated {
        public static final RRC_beamFailureRecoveryTimer MS10 = new RRC_beamFailureRecoveryTimer(0);
        public static final RRC_beamFailureRecoveryTimer MS20 = new RRC_beamFailureRecoveryTimer(1);
        public static final RRC_beamFailureRecoveryTimer MS40 = new RRC_beamFailureRecoveryTimer(2);
        public static final RRC_beamFailureRecoveryTimer MS60 = new RRC_beamFailureRecoveryTimer(3);
        public static final RRC_beamFailureRecoveryTimer MS80 = new RRC_beamFailureRecoveryTimer(4);
        public static final RRC_beamFailureRecoveryTimer MS100 = new RRC_beamFailureRecoveryTimer(5);
        public static final RRC_beamFailureRecoveryTimer MS150 = new RRC_beamFailureRecoveryTimer(6);
        public static final RRC_beamFailureRecoveryTimer MS200 = new RRC_beamFailureRecoveryTimer(7);
    
        private RRC_beamFailureRecoveryTimer(long value) {
            super(value);
        }
    }

    // SIZE(1..16)
    public static class RRC_candidateBeamRSList extends AsnSequenceOf<RRC_PRACH_ResourceDedicatedBFR> {
    }

    public static class RRC_ssb_perRACH_Occasion_3 extends AsnEnumerated {
        public static final RRC_ssb_perRACH_Occasion_3 ONEEIGHTH = new RRC_ssb_perRACH_Occasion_3(0);
        public static final RRC_ssb_perRACH_Occasion_3 ONEFOURTH = new RRC_ssb_perRACH_Occasion_3(1);
        public static final RRC_ssb_perRACH_Occasion_3 ONEHALF = new RRC_ssb_perRACH_Occasion_3(2);
        public static final RRC_ssb_perRACH_Occasion_3 ONE = new RRC_ssb_perRACH_Occasion_3(3);
        public static final RRC_ssb_perRACH_Occasion_3 TWO = new RRC_ssb_perRACH_Occasion_3(4);
        public static final RRC_ssb_perRACH_Occasion_3 FOUR = new RRC_ssb_perRACH_Occasion_3(5);
        public static final RRC_ssb_perRACH_Occasion_3 EIGHT = new RRC_ssb_perRACH_Occasion_3(6);
        public static final RRC_ssb_perRACH_Occasion_3 SIXTEEN = new RRC_ssb_perRACH_Occasion_3(7);
    
        private RRC_ssb_perRACH_Occasion_3(long value) {
            super(value);
        }
    }
}

