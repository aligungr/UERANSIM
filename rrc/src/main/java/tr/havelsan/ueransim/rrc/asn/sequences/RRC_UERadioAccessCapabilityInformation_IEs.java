/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_UERadioAccessCapabilityInformation_IEs extends RRC_Sequence {

    public RRC_OctetString ue_RadioAccessCapabilityInfo;
    public RRC_UERadioAccessCapabilityInformation_IEs__nonCriticalExtension nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ue-RadioAccessCapabilityInfo","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ue_RadioAccessCapabilityInfo","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "UERadioAccessCapabilityInformation-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "UERadioAccessCapabilityInformation-IEs";
    }

}
