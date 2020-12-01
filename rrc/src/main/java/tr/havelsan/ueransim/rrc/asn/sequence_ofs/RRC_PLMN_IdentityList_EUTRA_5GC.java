/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_PLMN_Identity_EUTRA_5GC;
import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;

public class RRC_PLMN_IdentityList_EUTRA_5GC extends RRC_SequenceOf<RRC_PLMN_Identity_EUTRA_5GC> {

    @Override
    public String getAsnName() {
        return "PLMN-IdentityList-EUTRA-5GC";
    }

    @Override
    public String getXmlTagName() {
        return "PLMN-IdentityList-EUTRA-5GC";
    }

    @Override
    public Class<RRC_PLMN_Identity_EUTRA_5GC> getItemType() {
        return RRC_PLMN_Identity_EUTRA_5GC.class;
    }

}
