/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_UE_CapabilityRAT_ContainerList;

public class RRC_UECapabilityInformation_IEs extends RRC_Sequence {

    public RRC_UE_CapabilityRAT_ContainerList ue_CapabilityRAT_ContainerList;
    public RRC_OctetString lateNonCriticalExtension;
    public RRC_UECapabilityInformation_IEs__nonCriticalExtension nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ue-CapabilityRAT-ContainerList","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ue_CapabilityRAT_ContainerList","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "UECapabilityInformation-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "UECapabilityInformation-IEs";
    }

}
