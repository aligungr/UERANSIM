/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SSB_ToMeasure;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Boolean;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.*;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MultiFrequencyBandListNR_SIB;

public class RRC_SIB2__intraFreqCellReselectionInfo extends RRC_Sequence {

    public RRC_Q_RxLevMin q_RxLevMin;
    public RRC_Q_RxLevMin q_RxLevMinSUL;
    public RRC_Q_QualMin q_QualMin;
    public RRC_ReselectionThreshold s_IntraSearchP;
    public RRC_ReselectionThresholdQ s_IntraSearchQ;
    public RRC_T_Reselection t_ReselectionNR;
    public RRC_MultiFrequencyBandListNR_SIB frequencyBandList;
    public RRC_MultiFrequencyBandListNR_SIB frequencyBandListSUL;
    public RRC_P_Max p_Max;
    public RRC_SSB_MTC smtc;
    public RRC_SS_RSSI_Measurement ss_RSSI_Measurement;
    public RRC_SSB_ToMeasure ssb_ToMeasure;
    public RRC_Boolean deriveSSB_IndexFromCell;
    public RRC_SIB2__intraFreqCellReselectionInfo__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "q-RxLevMin","q-RxLevMinSUL","q-QualMin","s-IntraSearchP","s-IntraSearchQ","t-ReselectionNR","frequencyBandList","frequencyBandListSUL","p-Max","smtc","ss-RSSI-Measurement","ssb-ToMeasure","deriveSSB-IndexFromCell","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "q_RxLevMin","q_RxLevMinSUL","q_QualMin","s_IntraSearchP","s_IntraSearchQ","t_ReselectionNR","frequencyBandList","frequencyBandListSUL","p_Max","smtc","ss_RSSI_Measurement","ssb_ToMeasure","deriveSSB_IndexFromCell","ext1" };
    }

}
