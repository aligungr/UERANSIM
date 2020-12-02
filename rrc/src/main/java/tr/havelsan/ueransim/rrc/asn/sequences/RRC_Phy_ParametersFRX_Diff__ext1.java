/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_Phy_ParametersFRX_Diff__ext1 extends RRC_Sequence {

    public RRC_CSI_RS_IM_ReceptionForFeedback csi_RS_IM_ReceptionForFeedback;
    public RRC_CSI_RS_ProcFrameworkForSRS csi_RS_ProcFrameworkForSRS;
    public RRC_CSI_ReportFramework csi_ReportFramework;
    public RRC_Phy_ParametersFRX_Diff__ext1__mux_SR_HARQ_ACK_CSI_PUCCH_OncePerSlot mux_SR_HARQ_ACK_CSI_PUCCH_OncePerSlot;
    public RRC_Integer mux_SR_HARQ_ACK_PUCCH;
    public RRC_Integer mux_MultipleGroupCtrlCH_Overlap;
    public RRC_Integer dl_SchedulingOffset_PDSCH_TypeA;
    public RRC_Integer dl_SchedulingOffset_PDSCH_TypeB;
    public RRC_Integer ul_SchedulingOffset;
    public RRC_Integer dl_64QAM_MCS_TableAlt;
    public RRC_Integer ul_64QAM_MCS_TableAlt;
    public RRC_Integer cqi_TableAlt;
    public RRC_Integer oneFL_DMRS_TwoAdditionalDMRS_UL;
    public RRC_Integer twoFL_DMRS_TwoAdditionalDMRS_UL;
    public RRC_Integer oneFL_DMRS_ThreeAdditionalDMRS_UL;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "csi-RS-IM-ReceptionForFeedback","csi-RS-ProcFrameworkForSRS","csi-ReportFramework","mux-SR-HARQ-ACK-CSI-PUCCH-OncePerSlot","mux-SR-HARQ-ACK-PUCCH","mux-MultipleGroupCtrlCH-Overlap","dl-SchedulingOffset-PDSCH-TypeA","dl-SchedulingOffset-PDSCH-TypeB","ul-SchedulingOffset","dl-64QAM-MCS-TableAlt","ul-64QAM-MCS-TableAlt","cqi-TableAlt","oneFL-DMRS-TwoAdditionalDMRS-UL","twoFL-DMRS-TwoAdditionalDMRS-UL","oneFL-DMRS-ThreeAdditionalDMRS-UL" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "csi_RS_IM_ReceptionForFeedback","csi_RS_ProcFrameworkForSRS","csi_ReportFramework","mux_SR_HARQ_ACK_CSI_PUCCH_OncePerSlot","mux_SR_HARQ_ACK_PUCCH","mux_MultipleGroupCtrlCH_Overlap","dl_SchedulingOffset_PDSCH_TypeA","dl_SchedulingOffset_PDSCH_TypeB","ul_SchedulingOffset","dl_64QAM_MCS_TableAlt","ul_64QAM_MCS_TableAlt","cqi_TableAlt","oneFL_DMRS_TwoAdditionalDMRS_UL","twoFL_DMRS_TwoAdditionalDMRS_UL","oneFL_DMRS_ThreeAdditionalDMRS_UL" };
    }

}
