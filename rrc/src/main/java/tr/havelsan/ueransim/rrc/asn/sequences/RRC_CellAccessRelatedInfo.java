/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PLMN_IdentityInfoList;

public class RRC_CellAccessRelatedInfo extends RRC_Sequence {

    public RRC_PLMN_IdentityInfoList plmn_IdentityList;
    public RRC_Integer cellReservedForOtherUse;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "plmn-IdentityList","cellReservedForOtherUse" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "plmn_IdentityList","cellReservedForOtherUse" };
    }

    @Override
    public String getAsnName() {
        return "CellAccessRelatedInfo";
    }

    @Override
    public String getXmlTagName() {
        return "CellAccessRelatedInfo";
    }

}
