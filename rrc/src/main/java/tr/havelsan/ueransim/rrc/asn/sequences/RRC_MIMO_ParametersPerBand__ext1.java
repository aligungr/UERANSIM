/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MIMO_ParametersPerBand__ext1__srs_AssocCSI_RS;

public class RRC_MIMO_ParametersPerBand__ext1 extends RRC_Sequence {

    public RRC_Integer dummy6;
    public RRC_BeamManagementSSB_CSI_RS beamManagementSSB_CSI_RS;
    public RRC_MIMO_ParametersPerBand__ext1__beamSwitchTiming beamSwitchTiming;
    public RRC_CodebookParameters codebookParameters;
    public RRC_CSI_RS_IM_ReceptionForFeedback csi_RS_IM_ReceptionForFeedback;
    public RRC_CSI_RS_ProcFrameworkForSRS csi_RS_ProcFrameworkForSRS;
    public RRC_CSI_ReportFramework csi_ReportFramework;
    public RRC_CSI_RS_ForTracking csi_RS_ForTracking;
    public RRC_MIMO_ParametersPerBand__ext1__srs_AssocCSI_RS srs_AssocCSI_RS;
    public RRC_SpatialRelations spatialRelations;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "dummy6","beamManagementSSB-CSI-RS","beamSwitchTiming","codebookParameters","csi-RS-IM-ReceptionForFeedback","csi-RS-ProcFrameworkForSRS","csi-ReportFramework","csi-RS-ForTracking","srs-AssocCSI-RS","spatialRelations" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "dummy6","beamManagementSSB_CSI_RS","beamSwitchTiming","codebookParameters","csi_RS_IM_ReceptionForFeedback","csi_RS_ProcFrameworkForSRS","csi_ReportFramework","csi_RS_ForTracking","srs_AssocCSI_RS","spatialRelations" };
    }

    @Override
    public String getAsnName() {
        throw new IllegalStateException("ASN.1 name is treated null for anonymous types.");
    }

    @Override
    public String getXmlTagName() {
        throw new IllegalStateException("XML tag name is treated null for anonymous types.");
    }

}
