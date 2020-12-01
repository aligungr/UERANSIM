/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_EUTRA_Q_OffsetRange;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_EUTRA_PhysCellId;

public class RRC_EUTRA_FreqNeighCellInfo extends RRC_Sequence {

    public RRC_EUTRA_PhysCellId physCellId;
    public RRC_EUTRA_Q_OffsetRange dummy;
    public RRC_Integer q_RxLevMinOffsetCell;
    public RRC_Integer q_QualMinOffsetCell;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "physCellId","dummy","q-RxLevMinOffsetCell","q-QualMinOffsetCell" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "physCellId","dummy","q_RxLevMinOffsetCell","q_QualMinOffsetCell" };
    }

    @Override
    public String getAsnName() {
        return "EUTRA-FreqNeighCellInfo";
    }

    @Override
    public String getXmlTagName() {
        return "EUTRA-FreqNeighCellInfo";
    }

}
