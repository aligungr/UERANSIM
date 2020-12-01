/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MIMO_ParametersPerBand extends RRC_Sequence {

    public RRC_MIMO_ParametersPerBand__tci_StatePDSCH tci_StatePDSCH;
    public RRC_Integer additionalActiveTCI_StatePDCCH;
    public RRC_Integer pusch_TransCoherence;
    public RRC_Integer beamCorrespondenceWithoutUL_BeamSweeping;
    public RRC_Integer periodicBeamReport;
    public RRC_Integer aperiodicBeamReport;
    public RRC_Integer sp_BeamReportPUCCH;
    public RRC_Integer sp_BeamReportPUSCH;
    public RRC_DummyG dummy1;
    public RRC_Integer maxNumberRxBeam;
    public RRC_MIMO_ParametersPerBand__maxNumberRxTxBeamSwitchDL maxNumberRxTxBeamSwitchDL;
    public RRC_Integer maxNumberNonGroupBeamReporting;
    public RRC_Integer groupBeamReporting;
    public RRC_MIMO_ParametersPerBand__uplinkBeamManagement uplinkBeamManagement;
    public RRC_Integer maxNumberCSI_RS_BFD;
    public RRC_Integer maxNumberSSB_BFD;
    public RRC_Integer maxNumberCSI_RS_SSB_CBD;
    public RRC_Integer dummy2;
    public RRC_Integer twoPortsPTRS_UL;
    public RRC_SRS_Resources dummy5;
    public RRC_Integer dummy3;
    public RRC_MIMO_ParametersPerBand__beamReportTiming beamReportTiming;
    public RRC_MIMO_ParametersPerBand__ptrs_DensityRecommendationSetDL ptrs_DensityRecommendationSetDL;
    public RRC_MIMO_ParametersPerBand__ptrs_DensityRecommendationSetUL ptrs_DensityRecommendationSetUL;
    public RRC_DummyH dummy4;
    public RRC_Integer aperiodicTRS;
    public RRC_MIMO_ParametersPerBand__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "tci-StatePDSCH","additionalActiveTCI-StatePDCCH","pusch-TransCoherence","beamCorrespondenceWithoutUL-BeamSweeping","periodicBeamReport","aperiodicBeamReport","sp-BeamReportPUCCH","sp-BeamReportPUSCH","dummy1","maxNumberRxBeam","maxNumberRxTxBeamSwitchDL","maxNumberNonGroupBeamReporting","groupBeamReporting","uplinkBeamManagement","maxNumberCSI-RS-BFD","maxNumberSSB-BFD","maxNumberCSI-RS-SSB-CBD","dummy2","twoPortsPTRS-UL","dummy5","dummy3","beamReportTiming","ptrs-DensityRecommendationSetDL","ptrs-DensityRecommendationSetUL","dummy4","aperiodicTRS","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "tci_StatePDSCH","additionalActiveTCI_StatePDCCH","pusch_TransCoherence","beamCorrespondenceWithoutUL_BeamSweeping","periodicBeamReport","aperiodicBeamReport","sp_BeamReportPUCCH","sp_BeamReportPUSCH","dummy1","maxNumberRxBeam","maxNumberRxTxBeamSwitchDL","maxNumberNonGroupBeamReporting","groupBeamReporting","uplinkBeamManagement","maxNumberCSI_RS_BFD","maxNumberSSB_BFD","maxNumberCSI_RS_SSB_CBD","dummy2","twoPortsPTRS_UL","dummy5","dummy3","beamReportTiming","ptrs_DensityRecommendationSetDL","ptrs_DensityRecommendationSetUL","dummy4","aperiodicTRS","ext1" };
    }

    @Override
    public String getAsnName() {
        return "MIMO-ParametersPerBand";
    }

    @Override
    public String getXmlTagName() {
        return "MIMO-ParametersPerBand";
    }

}
