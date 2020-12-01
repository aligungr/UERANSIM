/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PLMN_IdentityList_EUTRA_EPC;

public class RRC_CellAccessRelatedInfo_EUTRA_EPC extends RRC_Sequence {

    public RRC_PLMN_IdentityList_EUTRA_EPC plmn_IdentityList_eutra_epc;
    public RRC_BitString trackingAreaCode_eutra_epc;
    public RRC_BitString cellIdentity_eutra_epc;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "plmn-IdentityList-eutra-epc","trackingAreaCode-eutra-epc","cellIdentity-eutra-epc" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "plmn_IdentityList_eutra_epc","trackingAreaCode_eutra_epc","cellIdentity_eutra_epc" };
    }

    @Override
    public String getAsnName() {
        return "CellAccessRelatedInfo-EUTRA-EPC";
    }

    @Override
    public String getXmlTagName() {
        return "CellAccessRelatedInfo-EUTRA-EPC";
    }

}
