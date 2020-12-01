/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_EUTRA_CellIndex;

public class RRC_EUTRA_BlackCell extends RRC_Sequence {

    public RRC_EUTRA_CellIndex cellIndexEUTRA;
    public RRC_EUTRA_PhysCellIdRange physCellIdRange;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "cellIndexEUTRA","physCellIdRange" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "cellIndexEUTRA","physCellIdRange" };
    }

    @Override
    public String getAsnName() {
        return "EUTRA-BlackCell";
    }

    @Override
    public String getXmlTagName() {
        return "EUTRA-BlackCell";
    }

}
