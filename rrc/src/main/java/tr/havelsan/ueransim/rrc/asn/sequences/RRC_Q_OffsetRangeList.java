/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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

