/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.booleans.RRC_EUTRA_PresenceAntennaPort1;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_CellReselectionSubPriority;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_EUTRA_AllowedMeasBandwidth;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueEUTRA;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CellReselectionPriority;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ReselectionThreshold;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_EUTRA_FreqBlackCellList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_EUTRA_FreqNeighCellList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_EUTRA_MultiBandInfoList;

public class RRC_CarrierFreqEUTRA extends RRC_Sequence {

    public RRC_ARFCN_ValueEUTRA carrierFreq;
    public RRC_EUTRA_MultiBandInfoList eutra_multiBandInfoList;
    public RRC_EUTRA_FreqNeighCellList eutra_FreqNeighCellList;
    public RRC_EUTRA_FreqBlackCellList eutra_BlackCellList;
    public RRC_EUTRA_AllowedMeasBandwidth allowedMeasBandwidth;
    public RRC_EUTRA_PresenceAntennaPort1 presenceAntennaPort1;
    public RRC_CellReselectionPriority cellReselectionPriority;
    public RRC_CellReselectionSubPriority cellReselectionSubPriority;
    public RRC_ReselectionThreshold threshX_High;
    public RRC_ReselectionThreshold threshX_Low;
    public RRC_Integer q_RxLevMin;
    public RRC_Integer q_QualMin;
    public RRC_Integer p_MaxEUTRA;
    public RRC_CarrierFreqEUTRA__threshX_Q threshX_Q;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "carrierFreq","eutra-multiBandInfoList","eutra-FreqNeighCellList","eutra-BlackCellList","allowedMeasBandwidth","presenceAntennaPort1","cellReselectionPriority","cellReselectionSubPriority","threshX-High","threshX-Low","q-RxLevMin","q-QualMin","p-MaxEUTRA","threshX-Q" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "carrierFreq","eutra_multiBandInfoList","eutra_FreqNeighCellList","eutra_BlackCellList","allowedMeasBandwidth","presenceAntennaPort1","cellReselectionPriority","cellReselectionSubPriority","threshX_High","threshX_Low","q_RxLevMin","q_QualMin","p_MaxEUTRA","threshX_Q" };
    }

    @Override
    public String getAsnName() {
        return "CarrierFreqEUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "CarrierFreqEUTRA";
    }

}
