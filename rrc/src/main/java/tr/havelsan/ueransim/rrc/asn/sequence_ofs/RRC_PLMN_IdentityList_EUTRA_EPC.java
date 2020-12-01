/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_PLMN_Identity;

public class RRC_PLMN_IdentityList_EUTRA_EPC extends RRC_SequenceOf<RRC_PLMN_Identity> {

    @Override
    public String getAsnName() {
        return "PLMN-IdentityList-EUTRA-EPC";
    }

    @Override
    public String getXmlTagName() {
        return "PLMN-IdentityList-EUTRA-EPC";
    }

    @Override
    public Class<RRC_PLMN_Identity> getItemType() {
        return RRC_PLMN_Identity.class;
    }

}
