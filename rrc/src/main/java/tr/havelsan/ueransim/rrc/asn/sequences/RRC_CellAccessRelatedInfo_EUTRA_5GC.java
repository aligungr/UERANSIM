/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.bit_strings.RRC_TrackingAreaCode;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_CellIdentity_EUTRA_5GC;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RAN_AreaCode;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PLMN_IdentityList_EUTRA_5GC;

public class RRC_CellAccessRelatedInfo_EUTRA_5GC extends RRC_Sequence {

    public RRC_PLMN_IdentityList_EUTRA_5GC plmn_IdentityList_eutra_5gc;
    public RRC_TrackingAreaCode trackingAreaCode_eutra_5gc;
    public RRC_RAN_AreaCode ranac_5gc;
    public RRC_CellIdentity_EUTRA_5GC cellIdentity_eutra_5gc;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "plmn-IdentityList-eutra-5gc","trackingAreaCode-eutra-5gc","ranac-5gc","cellIdentity-eutra-5gc" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "plmn_IdentityList_eutra_5gc","trackingAreaCode_eutra_5gc","ranac_5gc","cellIdentity_eutra_5gc" };
    }

    @Override
    public String getAsnName() {
        return "CellAccessRelatedInfo-EUTRA-5GC";
    }

    @Override
    public String getXmlTagName() {
        return "CellAccessRelatedInfo-EUTRA-5GC";
    }

}
