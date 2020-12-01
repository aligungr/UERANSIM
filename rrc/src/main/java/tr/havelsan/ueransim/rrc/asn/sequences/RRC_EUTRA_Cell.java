/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_EUTRA_Q_OffsetRange;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_EUTRA_CellIndex;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_EUTRA_PhysCellId;

public class RRC_EUTRA_Cell extends RRC_Sequence {

    public RRC_EUTRA_CellIndex cellIndexEUTRA;
    public RRC_EUTRA_PhysCellId physCellId;
    public RRC_EUTRA_Q_OffsetRange cellIndividualOffset;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "cellIndexEUTRA","physCellId","cellIndividualOffset" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "cellIndexEUTRA","physCellId","cellIndividualOffset" };
    }

    @Override
    public String getAsnName() {
        return "EUTRA-Cell";
    }

    @Override
    public String getXmlTagName() {
        return "EUTRA-Cell";
    }

}
