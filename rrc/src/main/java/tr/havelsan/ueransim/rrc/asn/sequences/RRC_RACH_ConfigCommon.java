/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_RACH_ConfigCommon__prach_RootSequenceIndex;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_RACH_ConfigCommon__ssb_perRACH_OccasionAndCB_PreamblesPerSSB;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RSRP_Range;

public class RRC_RACH_ConfigCommon extends RRC_Sequence {

    public RRC_RACH_ConfigGeneric rach_ConfigGeneric;
    public RRC_Integer totalNumberOfRA_Preambles;
    public RRC_RACH_ConfigCommon__ssb_perRACH_OccasionAndCB_PreamblesPerSSB ssb_perRACH_OccasionAndCB_PreamblesPerSSB;
    public RRC_RACH_ConfigCommon__groupBconfigured groupBconfigured;
    public RRC_Integer ra_ContentionResolutionTimer;
    public RRC_RSRP_Range rsrp_ThresholdSSB;
    public RRC_RSRP_Range rsrp_ThresholdSSB_SUL;
    public RRC_RACH_ConfigCommon__prach_RootSequenceIndex prach_RootSequenceIndex;
    public RRC_SubcarrierSpacing msg1_SubcarrierSpacing;
    public RRC_Integer restrictedSetConfig;
    public RRC_Integer msg3_transformPrecoder;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "rach-ConfigGeneric","totalNumberOfRA-Preambles","ssb-perRACH-OccasionAndCB-PreamblesPerSSB","groupBconfigured","ra-ContentionResolutionTimer","rsrp-ThresholdSSB","rsrp-ThresholdSSB-SUL","prach-RootSequenceIndex","msg1-SubcarrierSpacing","restrictedSetConfig","msg3-transformPrecoder" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "rach_ConfigGeneric","totalNumberOfRA_Preambles","ssb_perRACH_OccasionAndCB_PreamblesPerSSB","groupBconfigured","ra_ContentionResolutionTimer","rsrp_ThresholdSSB","rsrp_ThresholdSSB_SUL","prach_RootSequenceIndex","msg1_SubcarrierSpacing","restrictedSetConfig","msg3_transformPrecoder" };
    }

    @Override
    public String getAsnName() {
        return "RACH-ConfigCommon";
    }

    @Override
    public String getXmlTagName() {
        return "RACH-ConfigCommon";
    }

}
