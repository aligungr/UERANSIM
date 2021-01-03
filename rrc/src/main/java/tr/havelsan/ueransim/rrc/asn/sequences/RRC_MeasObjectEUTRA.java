/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnBoolean;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.booleans.RRC_EUTRA_PresenceAntennaPort1;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_EUTRA_AllowedMeasBandwidth;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_EUTRA_Q_OffsetRange;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueEUTRA;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_EUTRA_CellIndexList;

public class RRC_MeasObjectEUTRA extends AsnSequence {
    public RRC_ARFCN_ValueEUTRA carrierFreq; // mandatory
    public RRC_EUTRA_AllowedMeasBandwidth allowedMeasBandwidth; // mandatory
    public RRC_EUTRA_CellIndexList cellsToRemoveListEUTRAN; // optional
    public RRC_cellsToAddModListEUTRAN cellsToAddModListEUTRAN; // optional, SIZE(1..32)
    public RRC_EUTRA_CellIndexList blackCellsToRemoveListEUTRAN; // optional
    public RRC_blackCellsToAddModListEUTRAN blackCellsToAddModListEUTRAN; // optional, SIZE(1..32)
    public RRC_EUTRA_PresenceAntennaPort1 eutra_PresenceAntennaPort1; // mandatory
    public RRC_EUTRA_Q_OffsetRange eutra_Q_OffsetRange; // optional
    public AsnBoolean widebandRSRQ_Meas; // mandatory

    // SIZE(1..32)
    public static class RRC_blackCellsToAddModListEUTRAN extends AsnSequenceOf<RRC_EUTRA_BlackCell> {
    }

    // SIZE(1..32)
    public static class RRC_cellsToAddModListEUTRAN extends AsnSequenceOf<RRC_EUTRA_Cell> {
    }
}

