/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RSRP_Range;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SearchSpaceId;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_BeamFailureRecoveryConfig__candidateBeamRSList;

public class RRC_BeamFailureRecoveryConfig extends RRC_Sequence {

    public RRC_Integer rootSequenceIndex_BFR;
    public RRC_RACH_ConfigGeneric rach_ConfigBFR;
    public RRC_RSRP_Range rsrp_ThresholdSSB;
    public RRC_BeamFailureRecoveryConfig__candidateBeamRSList candidateBeamRSList;
    public RRC_Integer ssb_perRACH_Occasion;
    public RRC_Integer ra_ssb_OccasionMaskIndex;
    public RRC_SearchSpaceId recoverySearchSpaceId;
    public RRC_RA_Prioritization ra_Prioritization;
    public RRC_Integer beamFailureRecoveryTimer;
    public RRC_BeamFailureRecoveryConfig__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "rootSequenceIndex-BFR","rach-ConfigBFR","rsrp-ThresholdSSB","candidateBeamRSList","ssb-perRACH-Occasion","ra-ssb-OccasionMaskIndex","recoverySearchSpaceId","ra-Prioritization","beamFailureRecoveryTimer","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "rootSequenceIndex_BFR","rach_ConfigBFR","rsrp_ThresholdSSB","candidateBeamRSList","ssb_perRACH_Occasion","ra_ssb_OccasionMaskIndex","recoverySearchSpaceId","ra_Prioritization","beamFailureRecoveryTimer","ext1" };
    }

    @Override
    public String getAsnName() {
        return "BeamFailureRecoveryConfig";
    }

    @Override
    public String getXmlTagName() {
        return "BeamFailureRecoveryConfig";
    }

}
