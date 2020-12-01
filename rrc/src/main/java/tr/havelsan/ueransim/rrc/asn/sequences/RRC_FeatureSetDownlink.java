/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_FreqSeparationClass;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.*;

public class RRC_FeatureSetDownlink extends RRC_Sequence {

    public RRC_FeatureSetDownlink__featureSetListPerDownlinkCC featureSetListPerDownlinkCC;
    public RRC_FreqSeparationClass intraBandFreqSeparationDL;
    public RRC_Integer scalingFactor;
    public RRC_Integer crossCarrierScheduling_OtherSCS;
    public RRC_Integer scellWithoutSSB;
    public RRC_Integer csi_RS_MeasSCellWithoutSSB;
    public RRC_Integer dummy1;
    public RRC_Integer type1_3_CSS;
    public RRC_Integer pdcch_MonitoringAnyOccasions;
    public RRC_Integer dummy2;
    public RRC_Integer ue_SpecificUL_DL_Assignment;
    public RRC_Integer searchSpaceSharingCA_DL;
    public RRC_FeatureSetDownlink__timeDurationForQCL timeDurationForQCL;
    public RRC_FeatureSetDownlink__pdsch_ProcessingType1_DifferentTB_PerSlot pdsch_ProcessingType1_DifferentTB_PerSlot;
    public RRC_DummyA dummy3;
    public RRC_FeatureSetDownlink__dummy4 dummy4;
    public RRC_FeatureSetDownlink__dummy5 dummy5;
    public RRC_FeatureSetDownlink__dummy6 dummy6;
    public RRC_FeatureSetDownlink__dummy7 dummy7;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "featureSetListPerDownlinkCC","intraBandFreqSeparationDL","scalingFactor","crossCarrierScheduling-OtherSCS","scellWithoutSSB","csi-RS-MeasSCellWithoutSSB","dummy1","type1-3-CSS","pdcch-MonitoringAnyOccasions","dummy2","ue-SpecificUL-DL-Assignment","searchSpaceSharingCA-DL","timeDurationForQCL","pdsch-ProcessingType1-DifferentTB-PerSlot","dummy3","dummy4","dummy5","dummy6","dummy7" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "featureSetListPerDownlinkCC","intraBandFreqSeparationDL","scalingFactor","crossCarrierScheduling_OtherSCS","scellWithoutSSB","csi_RS_MeasSCellWithoutSSB","dummy1","type1_3_CSS","pdcch_MonitoringAnyOccasions","dummy2","ue_SpecificUL_DL_Assignment","searchSpaceSharingCA_DL","timeDurationForQCL","pdsch_ProcessingType1_DifferentTB_PerSlot","dummy3","dummy4","dummy5","dummy6","dummy7" };
    }

    @Override
    public String getAsnName() {
        return "FeatureSetDownlink";
    }

    @Override
    public String getXmlTagName() {
        return "FeatureSetDownlink";
    }

}
