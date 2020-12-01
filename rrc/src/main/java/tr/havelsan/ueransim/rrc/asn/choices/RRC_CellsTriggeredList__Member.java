/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_EUTRA_PhysCellId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PhysCellId;

public class RRC_CellsTriggeredList__Member extends RRC_Choice {

    public RRC_PhysCellId physCellId;
    public RRC_EUTRA_PhysCellId physCellIdEUTRA;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "physCellId","physCellIdEUTRA" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "physCellId","physCellIdEUTRA" };
    }

    @Override
    public String getAsnName() {
        throw new IllegalStateException("ASN.1 name is treated null for anonymous types.");
    }

    @Override
    public String getXmlTagName() {
        throw new IllegalStateException("XML tag name is treated null for anonymous types.");
    }

}
