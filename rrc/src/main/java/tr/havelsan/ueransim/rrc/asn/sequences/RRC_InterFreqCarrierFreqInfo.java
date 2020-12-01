/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SSB_ToMeasure;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Boolean;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_CellReselectionSubPriority;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_Q_OffsetRange;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;
import tr.havelsan.ueransim.rrc.asn.integers.*;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_InterFreqBlackCellList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_InterFreqNeighCellList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MultiFrequencyBandListNR_SIB;

public class RRC_InterFreqCarrierFreqInfo extends RRC_Sequence {

    public RRC_ARFCN_ValueNR dl_CarrierFreq;
    public RRC_MultiFrequencyBandListNR_SIB frequencyBandList;
    public RRC_MultiFrequencyBandListNR_SIB frequencyBandListSUL;
    public RRC_Integer nrofSS_BlocksToAverage;
    public RRC_ThresholdNR absThreshSS_BlocksConsolidation;
    public RRC_SSB_MTC smtc;
    public RRC_SubcarrierSpacing ssbSubcarrierSpacing;
    public RRC_SSB_ToMeasure ssb_ToMeasure;
    public RRC_Boolean deriveSSB_IndexFromCell;
    public RRC_SS_RSSI_Measurement ss_RSSI_Measurement;
    public RRC_Q_RxLevMin q_RxLevMin;
    public RRC_Q_RxLevMin q_RxLevMinSUL;
    public RRC_Q_QualMin q_QualMin;
    public RRC_P_Max p_Max;
    public RRC_T_Reselection t_ReselectionNR;
    public RRC_SpeedStateScaleFactors t_ReselectionNR_SF;
    public RRC_ReselectionThreshold threshX_HighP;
    public RRC_ReselectionThreshold threshX_LowP;
    public RRC_InterFreqCarrierFreqInfo__threshX_Q threshX_Q;
    public RRC_CellReselectionPriority cellReselectionPriority;
    public RRC_CellReselectionSubPriority cellReselectionSubPriority;
    public RRC_Q_OffsetRange q_OffsetFreq;
    public RRC_InterFreqNeighCellList interFreqNeighCellList;
    public RRC_InterFreqBlackCellList interFreqBlackCellList;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "dl-CarrierFreq","frequencyBandList","frequencyBandListSUL","nrofSS-BlocksToAverage","absThreshSS-BlocksConsolidation","smtc","ssbSubcarrierSpacing","ssb-ToMeasure","deriveSSB-IndexFromCell","ss-RSSI-Measurement","q-RxLevMin","q-RxLevMinSUL","q-QualMin","p-Max","t-ReselectionNR","t-ReselectionNR-SF","threshX-HighP","threshX-LowP","threshX-Q","cellReselectionPriority","cellReselectionSubPriority","q-OffsetFreq","interFreqNeighCellList","interFreqBlackCellList" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "dl_CarrierFreq","frequencyBandList","frequencyBandListSUL","nrofSS_BlocksToAverage","absThreshSS_BlocksConsolidation","smtc","ssbSubcarrierSpacing","ssb_ToMeasure","deriveSSB_IndexFromCell","ss_RSSI_Measurement","q_RxLevMin","q_RxLevMinSUL","q_QualMin","p_Max","t_ReselectionNR","t_ReselectionNR_SF","threshX_HighP","threshX_LowP","threshX_Q","cellReselectionPriority","cellReselectionSubPriority","q_OffsetFreq","interFreqNeighCellList","interFreqBlackCellList" };
    }

    @Override
    public String getAsnName() {
        return "InterFreqCarrierFreqInfo";
    }

    @Override
    public String getXmlTagName() {
        return "InterFreqCarrierFreqInfo";
    }

}
