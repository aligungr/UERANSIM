/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_RAT_Type;

public class RRC_UE_CapabilityRAT_Container extends RRC_Sequence {

    public RRC_RAT_Type rat_Type;
    public RRC_OctetString ue_CapabilityRAT_Container;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "rat-Type","ue-CapabilityRAT-Container" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "rat_Type","ue_CapabilityRAT_Container" };
    }

    @Override
    public String getAsnName() {
        return "UE-CapabilityRAT-Container";
    }

    @Override
    public String getXmlTagName() {
        return "UE-CapabilityRAT-Container";
    }

}
