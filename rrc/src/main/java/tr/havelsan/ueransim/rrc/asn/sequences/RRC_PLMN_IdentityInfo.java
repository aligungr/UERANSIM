/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.bit_strings.RRC_CellIdentity;
import tr.havelsan.ueransim.rrc.asn.bit_strings.RRC_TrackingAreaCode;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RAN_AreaCode;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PLMN_IdentityInfo__plmn_IdentityList;

public class RRC_PLMN_IdentityInfo extends RRC_Sequence {

    public RRC_PLMN_IdentityInfo__plmn_IdentityList plmn_IdentityList;
    public RRC_TrackingAreaCode trackingAreaCode;
    public RRC_RAN_AreaCode ranac;
    public RRC_CellIdentity cellIdentity;
    public RRC_Integer cellReservedForOperatorUse;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "plmn-IdentityList","trackingAreaCode","ranac","cellIdentity","cellReservedForOperatorUse" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "plmn_IdentityList","trackingAreaCode","ranac","cellIdentity","cellReservedForOperatorUse" };
    }

    @Override
    public String getAsnName() {
        return "PLMN-IdentityInfo";
    }

    @Override
    public String getXmlTagName() {
        return "PLMN-IdentityInfo";
    }

}
