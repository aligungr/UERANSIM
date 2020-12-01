/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.octet_strings.RRC_DedicatedNAS_Message;

public class RRC_ULInformationTransfer_IEs extends RRC_Sequence {

    public RRC_DedicatedNAS_Message dedicatedNAS_Message;
    public RRC_OctetString lateNonCriticalExtension;
    public RRC_ULInformationTransfer_IEs__nonCriticalExtension nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "dedicatedNAS-Message","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "dedicatedNAS_Message","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "ULInformationTransfer-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "ULInformationTransfer-IEs";
    }

}
