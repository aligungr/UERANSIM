/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_UECapabilityEnquiry_v1560_IEs extends RRC_Sequence {

    public RRC_UE_CapabilityRequestFilterCommon capabilityRequestFilterCommon;
    public RRC_UECapabilityEnquiry_v1560_IEs__nonCriticalExtension nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "capabilityRequestFilterCommon","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "capabilityRequestFilterCommon","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "UECapabilityEnquiry-v1560-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "UECapabilityEnquiry-v1560-IEs";
    }

}
