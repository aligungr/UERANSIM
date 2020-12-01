/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_Q_OffsetRange;

public class RRC_Q_OffsetRangeList extends RRC_Sequence {

    public RRC_Q_OffsetRange rsrpOffsetSSB;
    public RRC_Q_OffsetRange rsrqOffsetSSB;
    public RRC_Q_OffsetRange sinrOffsetSSB;
    public RRC_Q_OffsetRange rsrpOffsetCSI_RS;
    public RRC_Q_OffsetRange rsrqOffsetCSI_RS;
    public RRC_Q_OffsetRange sinrOffsetCSI_RS;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "rsrpOffsetSSB","rsrqOffsetSSB","sinrOffsetSSB","rsrpOffsetCSI-RS","rsrqOffsetCSI-RS","sinrOffsetCSI-RS" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "rsrpOffsetSSB","rsrqOffsetSSB","sinrOffsetSSB","rsrpOffsetCSI_RS","rsrqOffsetCSI_RS","sinrOffsetCSI_RS" };
    }

    @Override
    public String getAsnName() {
        return "Q-OffsetRangeList";
    }

    @Override
    public String getXmlTagName() {
        return "Q-OffsetRangeList";
    }

}
