/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;

public class RRC_CellIdentity_EUTRA_5GC extends RRC_Choice {

    public RRC_BitString cellIdentity_EUTRA;
    public RRC_Integer cellId_index;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "cellIdentity-EUTRA","cellId-index" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "cellIdentity_EUTRA","cellId_index" };
    }

    @Override
    public String getAsnName() {
        return "CellIdentity-EUTRA-5GC";
    }

    @Override
    public String getXmlTagName() {
        return "CellIdentity-EUTRA-5GC";
    }

}
