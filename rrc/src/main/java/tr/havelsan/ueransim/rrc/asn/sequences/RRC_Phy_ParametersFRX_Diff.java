/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_Phy_ParametersFRX_Diff extends RRC_Sequence {

    public RRC_Integer dynamicSFI;
    public RRC_BitString dummy1;
    public RRC_BitString twoFL_DMRS;
    public RRC_BitString dummy2;
    public RRC_BitString dummy3;
    public RRC_Integer supportedDMRS_TypeDL;
    public RRC_Integer supportedDMRS_TypeUL;
    public RRC_Integer semiOpenLoopCSI;
    public RRC_Integer csi_ReportWithoutPMI;
    public RRC_Integer csi_ReportWithoutCQI;
    public RRC_BitString onePortsPTRS;
    public RRC_Integer twoPUCCH_F0_2_ConsecSymbols;
    public RRC_Integer pucch_F2_WithFH;
    public RRC_Integer pucch_F3_WithFH;
    public RRC_Integer pucch_F4_WithFH;
    public RRC_Integer freqHoppingPUCCH_F0_2;
    public RRC_Integer freqHoppingPUCCH_F1_3_4;
    public RRC_Integer mux_SR_HARQ_ACK_CSI_PUCCH_MultiPerSlot;
    public RRC_Integer uci_CodeBlockSegmentation;
    public RRC_Integer onePUCCH_LongAndShortFormat;
    public RRC_Integer twoPUCCH_AnyOthersInSlot;
    public RRC_Integer intraSlotFreqHopping_PUSCH;
    public RRC_Integer pusch_LBRM;
    public RRC_Integer pdcch_BlindDetectionCA;
    public RRC_Integer tpc_PUSCH_RNTI;
    public RRC_Integer tpc_PUCCH_RNTI;
    public RRC_Integer tpc_SRS_RNTI;
    public RRC_Integer absoluteTPC_Command;
    public RRC_Integer twoDifferentTPC_Loop_PUSCH;
    public RRC_Integer twoDifferentTPC_Loop_PUCCH;
    public RRC_Integer pusch_HalfPi_BPSK;
    public RRC_Integer pucch_F3_4_HalfPi_BPSK;
    public RRC_Integer almostContiguousCP_OFDM_UL;
    public RRC_Integer sp_CSI_RS;
    public RRC_Integer sp_CSI_IM;
    public RRC_Integer tdd_MultiDL_UL_SwitchPerSlot;
    public RRC_Integer multipleCORESET;
    public RRC_Phy_ParametersFRX_Diff__ext1 ext1;
    public RRC_Phy_ParametersFRX_Diff__ext2 ext2;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "dynamicSFI","dummy1","twoFL-DMRS","dummy2","dummy3","supportedDMRS-TypeDL","supportedDMRS-TypeUL","semiOpenLoopCSI","csi-ReportWithoutPMI","csi-ReportWithoutCQI","onePortsPTRS","twoPUCCH-F0-2-ConsecSymbols","pucch-F2-WithFH","pucch-F3-WithFH","pucch-F4-WithFH","freqHoppingPUCCH-F0-2","freqHoppingPUCCH-F1-3-4","mux-SR-HARQ-ACK-CSI-PUCCH-MultiPerSlot","uci-CodeBlockSegmentation","onePUCCH-LongAndShortFormat","twoPUCCH-AnyOthersInSlot","intraSlotFreqHopping-PUSCH","pusch-LBRM","pdcch-BlindDetectionCA","tpc-PUSCH-RNTI","tpc-PUCCH-RNTI","tpc-SRS-RNTI","absoluteTPC-Command","twoDifferentTPC-Loop-PUSCH","twoDifferentTPC-Loop-PUCCH","pusch-HalfPi-BPSK","pucch-F3-4-HalfPi-BPSK","almostContiguousCP-OFDM-UL","sp-CSI-RS","sp-CSI-IM","tdd-MultiDL-UL-SwitchPerSlot","multipleCORESET","ext1","ext2" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "dynamicSFI","dummy1","twoFL_DMRS","dummy2","dummy3","supportedDMRS_TypeDL","supportedDMRS_TypeUL","semiOpenLoopCSI","csi_ReportWithoutPMI","csi_ReportWithoutCQI","onePortsPTRS","twoPUCCH_F0_2_ConsecSymbols","pucch_F2_WithFH","pucch_F3_WithFH","pucch_F4_WithFH","freqHoppingPUCCH_F0_2","freqHoppingPUCCH_F1_3_4","mux_SR_HARQ_ACK_CSI_PUCCH_MultiPerSlot","uci_CodeBlockSegmentation","onePUCCH_LongAndShortFormat","twoPUCCH_AnyOthersInSlot","intraSlotFreqHopping_PUSCH","pusch_LBRM","pdcch_BlindDetectionCA","tpc_PUSCH_RNTI","tpc_PUCCH_RNTI","tpc_SRS_RNTI","absoluteTPC_Command","twoDifferentTPC_Loop_PUSCH","twoDifferentTPC_Loop_PUCCH","pusch_HalfPi_BPSK","pucch_F3_4_HalfPi_BPSK","almostContiguousCP_OFDM_UL","sp_CSI_RS","sp_CSI_IM","tdd_MultiDL_UL_SwitchPerSlot","multipleCORESET","ext1","ext2" };
    }

    @Override
    public String getAsnName() {
        return "Phy-ParametersFRX-Diff";
    }

    @Override
    public String getXmlTagName() {
        return "Phy-ParametersFRX-Diff";
    }

}
