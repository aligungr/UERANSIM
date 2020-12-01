/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_FreqSeparationClass;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_FeatureSetUplink__featureSetListPerUplinkCC;

public class RRC_FeatureSetUplink extends RRC_Sequence {

    public RRC_FeatureSetUplink__featureSetListPerUplinkCC featureSetListPerUplinkCC;
    public RRC_Integer scalingFactor;
    public RRC_Integer crossCarrierScheduling_OtherSCS;
    public RRC_FreqSeparationClass intraBandFreqSeparationUL;
    public RRC_Integer searchSpaceSharingCA_UL;
    public RRC_DummyI dummy1;
    public RRC_SRS_Resources supportedSRS_Resources;
    public RRC_Integer twoPUCCH_Group;
    public RRC_Integer dynamicSwitchSUL;
    public RRC_Integer simultaneousTxSUL_NonSUL;
    public RRC_FeatureSetUplink__pusch_ProcessingType1_DifferentTB_PerSlot pusch_ProcessingType1_DifferentTB_PerSlot;
    public RRC_DummyF dummy2;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "featureSetListPerUplinkCC","scalingFactor","crossCarrierScheduling-OtherSCS","intraBandFreqSeparationUL","searchSpaceSharingCA-UL","dummy1","supportedSRS-Resources","twoPUCCH-Group","dynamicSwitchSUL","simultaneousTxSUL-NonSUL","pusch-ProcessingType1-DifferentTB-PerSlot","dummy2" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "featureSetListPerUplinkCC","scalingFactor","crossCarrierScheduling_OtherSCS","intraBandFreqSeparationUL","searchSpaceSharingCA_UL","dummy1","supportedSRS_Resources","twoPUCCH_Group","dynamicSwitchSUL","simultaneousTxSUL_NonSUL","pusch_ProcessingType1_DifferentTB_PerSlot","dummy2" };
    }

    @Override
    public String getAsnName() {
        return "FeatureSetUplink";
    }

    @Override
    public String getXmlTagName() {
        return "FeatureSetUplink";
    }

}
