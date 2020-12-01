/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.bit_strings.RRC_CellIdentity;
import tr.havelsan.ueransim.rrc.asn.bit_strings.RRC_ShortMAC_I;
import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_ReestabNCellInfo extends RRC_Sequence {

    public RRC_CellIdentity cellIdentity;
    public RRC_BitString key_gNodeB_Star;
    public RRC_ShortMAC_I shortMAC_I;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "cellIdentity","key-gNodeB-Star","shortMAC-I" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "cellIdentity","key_gNodeB_Star","shortMAC_I" };
    }

    @Override
    public String getAsnName() {
        return "ReestabNCellInfo";
    }

    @Override
    public String getXmlTagName() {
        return "ReestabNCellInfo";
    }

}
