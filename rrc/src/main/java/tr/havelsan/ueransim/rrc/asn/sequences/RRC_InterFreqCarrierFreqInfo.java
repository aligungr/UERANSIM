/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnBoolean;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SSB_ToMeasure;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_CellReselectionSubPriority;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_Q_OffsetRange;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;
import tr.havelsan.ueransim.rrc.asn.integers.*;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_InterFreqBlackCellList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_InterFreqNeighCellList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MultiFrequencyBandListNR_SIB;

public class RRC_InterFreqCarrierFreqInfo extends AsnSequence {
    public RRC_ARFCN_ValueNR dl_CarrierFreq; // mandatory
    public RRC_MultiFrequencyBandListNR_SIB frequencyBandList; // optional
    public RRC_MultiFrequencyBandListNR_SIB frequencyBandListSUL; // optional
    public AsnInteger nrofSS_BlocksToAverage; // optional, VALUE(2..16)
    public RRC_ThresholdNR absThreshSS_BlocksConsolidation; // optional
    public RRC_SSB_MTC smtc; // optional
    public RRC_SubcarrierSpacing ssbSubcarrierSpacing; // mandatory
    public RRC_SSB_ToMeasure ssb_ToMeasure; // optional
    public AsnBoolean deriveSSB_IndexFromCell; // mandatory
    public RRC_SS_RSSI_Measurement ss_RSSI_Measurement; // optional
    public RRC_Q_RxLevMin q_RxLevMin; // mandatory
    public RRC_Q_RxLevMin q_RxLevMinSUL; // optional
    public RRC_Q_QualMin q_QualMin; // optional
    public RRC_P_Max p_Max; // optional
    public RRC_T_Reselection t_ReselectionNR; // mandatory
    public RRC_SpeedStateScaleFactors t_ReselectionNR_SF; // optional
    public RRC_ReselectionThreshold threshX_HighP; // mandatory
    public RRC_ReselectionThreshold threshX_LowP; // mandatory
    public RRC_threshX_Q_2 threshX_Q; // optional
    public RRC_CellReselectionPriority cellReselectionPriority; // optional
    public RRC_CellReselectionSubPriority cellReselectionSubPriority; // optional
    public RRC_Q_OffsetRange q_OffsetFreq; // optional
    public RRC_InterFreqNeighCellList interFreqNeighCellList; // optional
    public RRC_InterFreqBlackCellList interFreqBlackCellList; // optional

    public static class RRC_threshX_Q_2 extends AsnSequence {
        public RRC_ReselectionThresholdQ threshX_HighQ; // mandatory
        public RRC_ReselectionThresholdQ threshX_LowQ; // mandatory
    }
}

