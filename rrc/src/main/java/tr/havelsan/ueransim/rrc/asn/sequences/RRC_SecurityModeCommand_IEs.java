/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_SecurityModeCommand_IEs extends RRC_Sequence {

    public RRC_SecurityConfigSMC securityConfigSMC;
    public RRC_OctetString lateNonCriticalExtension;
    public RRC_SecurityModeCommand_IEs__nonCriticalExtension nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "securityConfigSMC","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "securityConfigSMC","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "SecurityModeCommand-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "SecurityModeCommand-IEs";
    }

}
