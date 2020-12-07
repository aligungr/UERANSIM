/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_Q_OffsetRange;

public class RRC_Q_OffsetRangeList extends AsnSequence {
    public RRC_Q_OffsetRange rsrpOffsetSSB; // optional
    public RRC_Q_OffsetRange rsrqOffsetSSB; // optional
    public RRC_Q_OffsetRange sinrOffsetSSB; // optional
    public RRC_Q_OffsetRange rsrpOffsetCSI_RS; // optional
    public RRC_Q_OffsetRange rsrqOffsetCSI_RS; // optional
    public RRC_Q_OffsetRange sinrOffsetCSI_RS; // optional
}

