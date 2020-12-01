/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PhysCellId;

public class RRC_CellsToAddMod extends RRC_Sequence {

    public RRC_PhysCellId physCellId;
    public RRC_Q_OffsetRangeList cellIndividualOffset;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "physCellId","cellIndividualOffset" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "physCellId","cellIndividualOffset" };
    }

    @Override
    public String getAsnName() {
        return "CellsToAddMod";
    }

    @Override
    public String getXmlTagName() {
        return "CellsToAddMod";
    }

}
