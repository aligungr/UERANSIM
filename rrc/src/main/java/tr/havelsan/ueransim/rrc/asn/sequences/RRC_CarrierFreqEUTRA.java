/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.booleans.RRC_EUTRA_PresenceAntennaPort1;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_CellReselectionSubPriority;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_EUTRA_AllowedMeasBandwidth;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueEUTRA;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CellReselectionPriority;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ReselectionThreshold;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ReselectionThresholdQ;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_EUTRA_FreqBlackCellList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_EUTRA_FreqNeighCellList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_EUTRA_MultiBandInfoList;

public class RRC_CarrierFreqEUTRA extends AsnSequence {
    public RRC_ARFCN_ValueEUTRA carrierFreq; // mandatory
    public RRC_EUTRA_MultiBandInfoList eutra_multiBandInfoList; // optional
    public RRC_EUTRA_FreqNeighCellList eutra_FreqNeighCellList; // optional
    public RRC_EUTRA_FreqBlackCellList eutra_BlackCellList; // optional
    public RRC_EUTRA_AllowedMeasBandwidth allowedMeasBandwidth; // mandatory
    public RRC_EUTRA_PresenceAntennaPort1 presenceAntennaPort1; // mandatory
    public RRC_CellReselectionPriority cellReselectionPriority; // optional
    public RRC_CellReselectionSubPriority cellReselectionSubPriority; // optional
    public RRC_ReselectionThreshold threshX_High; // mandatory
    public RRC_ReselectionThreshold threshX_Low; // mandatory
    public AsnInteger q_RxLevMin; // mandatory, VALUE(-70..-22)
    public AsnInteger q_QualMin; // mandatory, VALUE(-34..-3)
    public AsnInteger p_MaxEUTRA; // mandatory, VALUE(-30..33)
    public RRC_threshX_Q_1 threshX_Q; // optional

    public static class RRC_threshX_Q_1 extends AsnSequence {
        public RRC_ReselectionThresholdQ threshX_HighQ; // mandatory
        public RRC_ReselectionThresholdQ threshX_LowQ; // mandatory
    }
}

