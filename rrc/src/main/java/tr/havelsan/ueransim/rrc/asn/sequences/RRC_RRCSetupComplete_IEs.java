/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_RRCSetupComplete_IEs__ng_5G_S_TMSI_Value;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.octet_strings.RRC_DedicatedNAS_Message;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_RRCSetupComplete_IEs__s_NSSAI_List;

public class RRC_RRCSetupComplete_IEs extends RRC_Sequence {

    public RRC_Integer selectedPLMN_Identity;
    public RRC_RegisteredAMF registeredAMF;
    public RRC_Integer guami_Type;
    public RRC_RRCSetupComplete_IEs__s_NSSAI_List s_NSSAI_List;
    public RRC_DedicatedNAS_Message dedicatedNAS_Message;
    public RRC_RRCSetupComplete_IEs__ng_5G_S_TMSI_Value ng_5G_S_TMSI_Value;
    public RRC_OctetString lateNonCriticalExtension;
    public RRC_RRCSetupComplete_IEs__nonCriticalExtension nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "selectedPLMN-Identity","registeredAMF","guami-Type","s-NSSAI-List","dedicatedNAS-Message","ng-5G-S-TMSI-Value","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "selectedPLMN_Identity","registeredAMF","guami_Type","s_NSSAI_List","dedicatedNAS_Message","ng_5G_S_TMSI_Value","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "RRCSetupComplete-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "RRCSetupComplete-IEs";
    }

}
