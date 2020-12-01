/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_HandoverCommand_IEs extends RRC_Sequence {

    public RRC_OctetString handoverCommandMessage;
    public RRC_HandoverCommand_IEs__nonCriticalExtension nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "handoverCommandMessage","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "handoverCommandMessage","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "HandoverCommand-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "HandoverCommand-IEs";
    }

}
