/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_CSI_ReportConfig__groupBasedBeamReporting;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_CSI_ReportConfig__reportConfigType;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_CSI_ReportConfig__reportQuantity;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CSI_ReportConfigId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CSI_ResourceConfigId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CSI_ReportConfig__non_PMI_PortIndication;

public class RRC_CSI_ReportConfig extends RRC_Sequence {

    public RRC_CSI_ReportConfigId reportConfigId;
    public RRC_ServCellIndex carrier;
    public RRC_CSI_ResourceConfigId resourcesForChannelMeasurement;
    public RRC_CSI_ResourceConfigId csi_IM_ResourcesForInterference;
    public RRC_CSI_ResourceConfigId nzp_CSI_RS_ResourcesForInterference;
    public RRC_CSI_ReportConfig__reportConfigType reportConfigType;
    public RRC_CSI_ReportConfig__reportQuantity reportQuantity;
    public RRC_CSI_ReportConfig__reportFreqConfiguration reportFreqConfiguration;
    public RRC_Integer timeRestrictionForChannelMeasurements;
    public RRC_Integer timeRestrictionForInterferenceMeasurements;
    public RRC_CodebookConfig codebookConfig;
    public RRC_Integer dummy;
    public RRC_CSI_ReportConfig__groupBasedBeamReporting groupBasedBeamReporting;
    public RRC_Integer cqi_Table;
    public RRC_Integer subbandSize;
    public RRC_CSI_ReportConfig__non_PMI_PortIndication non_PMI_PortIndication;
    public RRC_CSI_ReportConfig__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "reportConfigId","carrier","resourcesForChannelMeasurement","csi-IM-ResourcesForInterference","nzp-CSI-RS-ResourcesForInterference","reportConfigType","reportQuantity","reportFreqConfiguration","timeRestrictionForChannelMeasurements","timeRestrictionForInterferenceMeasurements","codebookConfig","dummy","groupBasedBeamReporting","cqi-Table","subbandSize","non-PMI-PortIndication","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "reportConfigId","carrier","resourcesForChannelMeasurement","csi_IM_ResourcesForInterference","nzp_CSI_RS_ResourcesForInterference","reportConfigType","reportQuantity","reportFreqConfiguration","timeRestrictionForChannelMeasurements","timeRestrictionForInterferenceMeasurements","codebookConfig","dummy","groupBasedBeamReporting","cqi_Table","subbandSize","non_PMI_PortIndication","ext1" };
    }

    @Override
    public String getAsnName() {
        return "CSI-ReportConfig";
    }

    @Override
    public String getXmlTagName() {
        return "CSI-ReportConfig";
    }

}
