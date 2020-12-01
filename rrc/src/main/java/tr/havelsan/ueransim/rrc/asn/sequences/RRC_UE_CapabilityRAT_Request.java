/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_RAT_Type;

public class RRC_UE_CapabilityRAT_Request extends RRC_Sequence {

    public RRC_RAT_Type rat_Type;
    public RRC_OctetString capabilityRequestFilter;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "rat-Type","capabilityRequestFilter" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "rat_Type","capabilityRequestFilter" };
    }

    @Override
    public String getAsnName() {
        return "UE-CapabilityRAT-Request";
    }

    @Override
    public String getXmlTagName() {
        return "UE-CapabilityRAT-Request";
    }

}
