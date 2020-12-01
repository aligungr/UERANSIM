/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_SystemInformation_IEs__sib_TypeAndInfo;

public class RRC_SystemInformation_IEs extends RRC_Sequence {

    public RRC_SystemInformation_IEs__sib_TypeAndInfo sib_TypeAndInfo;
    public RRC_OctetString lateNonCriticalExtension;
    public RRC_SystemInformation_IEs__nonCriticalExtension nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "sib-TypeAndInfo","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "sib_TypeAndInfo","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "SystemInformation-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "SystemInformation-IEs";
    }

}
