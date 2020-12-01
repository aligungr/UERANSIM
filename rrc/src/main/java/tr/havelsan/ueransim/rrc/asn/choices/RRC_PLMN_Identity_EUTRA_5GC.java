/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_PLMN_Identity;

public class RRC_PLMN_Identity_EUTRA_5GC extends RRC_Choice {

    public RRC_PLMN_Identity plmn_Identity_EUTRA_5GC;
    public RRC_Integer plmn_index;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "plmn-Identity-EUTRA-5GC","plmn-index" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "plmn_Identity_EUTRA_5GC","plmn_index" };
    }

    @Override
    public String getAsnName() {
        return "PLMN-Identity-EUTRA-5GC";
    }

    @Override
    public String getXmlTagName() {
        return "PLMN-Identity-EUTRA-5GC";
    }

}
