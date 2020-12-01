/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.booleans.RRC_EUTRA_PresenceAntennaPort1;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Boolean;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_EUTRA_AllowedMeasBandwidth;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_EUTRA_Q_OffsetRange;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueEUTRA;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_EUTRA_CellIndexList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MeasObjectEUTRA__blackCellsToAddModListEUTRAN;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MeasObjectEUTRA__cellsToAddModListEUTRAN;

public class RRC_MeasObjectEUTRA extends RRC_Sequence {

    public RRC_ARFCN_ValueEUTRA carrierFreq;
    public RRC_EUTRA_AllowedMeasBandwidth allowedMeasBandwidth;
    public RRC_EUTRA_CellIndexList cellsToRemoveListEUTRAN;
    public RRC_MeasObjectEUTRA__cellsToAddModListEUTRAN cellsToAddModListEUTRAN;
    public RRC_EUTRA_CellIndexList blackCellsToRemoveListEUTRAN;
    public RRC_MeasObjectEUTRA__blackCellsToAddModListEUTRAN blackCellsToAddModListEUTRAN;
    public RRC_EUTRA_PresenceAntennaPort1 eutra_PresenceAntennaPort1;
    public RRC_EUTRA_Q_OffsetRange eutra_Q_OffsetRange;
    public RRC_Boolean widebandRSRQ_Meas;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "carrierFreq","allowedMeasBandwidth","cellsToRemoveListEUTRAN","cellsToAddModListEUTRAN","blackCellsToRemoveListEUTRAN","blackCellsToAddModListEUTRAN","eutra-PresenceAntennaPort1","eutra-Q-OffsetRange","widebandRSRQ-Meas" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "carrierFreq","allowedMeasBandwidth","cellsToRemoveListEUTRAN","cellsToAddModListEUTRAN","blackCellsToRemoveListEUTRAN","blackCellsToAddModListEUTRAN","eutra_PresenceAntennaPort1","eutra_Q_OffsetRange","widebandRSRQ_Meas" };
    }

    @Override
    public String getAsnName() {
        return "MeasObjectEUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "MeasObjectEUTRA";
    }

}
