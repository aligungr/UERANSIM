/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_Q_OffsetRange;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PhysCellId;

public class RRC_InterFreqNeighCellInfo extends RRC_Sequence {

    public RRC_PhysCellId physCellId;
    public RRC_Q_OffsetRange q_OffsetCell;
    public RRC_Integer q_RxLevMinOffsetCell;
    public RRC_Integer q_RxLevMinOffsetCellSUL;
    public RRC_Integer q_QualMinOffsetCell;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "physCellId","q-OffsetCell","q-RxLevMinOffsetCell","q-RxLevMinOffsetCellSUL","q-QualMinOffsetCell" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "physCellId","q_OffsetCell","q_RxLevMinOffsetCell","q_RxLevMinOffsetCellSUL","q_QualMinOffsetCell" };
    }

    @Override
    public String getAsnName() {
        return "InterFreqNeighCellInfo";
    }

    @Override
    public String getXmlTagName() {
        return "InterFreqNeighCellInfo";
    }

}
