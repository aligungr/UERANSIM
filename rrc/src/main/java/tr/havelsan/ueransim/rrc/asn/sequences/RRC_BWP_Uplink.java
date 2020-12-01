/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_BWP_Id;

public class RRC_BWP_Uplink extends RRC_Sequence {

    public RRC_BWP_Id bwp_Id;
    public RRC_BWP_UplinkCommon bwp_Common;
    public RRC_BWP_UplinkDedicated bwp_Dedicated;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "bwp-Id","bwp-Common","bwp-Dedicated" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "bwp_Id","bwp_Common","bwp_Dedicated" };
    }

    @Override
    public String getAsnName() {
        return "BWP-Uplink";
    }

    @Override
    public String getXmlTagName() {
        return "BWP-Uplink";
    }

}
