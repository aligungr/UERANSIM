/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_MRDC_SecondaryCellGroupConfig;
import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SK_Counter;

public class RRC_RRCReconfiguration_v1560_IEs extends RRC_Sequence {

    public RRC_SetupRelease_MRDC_SecondaryCellGroupConfig mrdc_SecondaryCellGroupConfig;
    public RRC_OctetString radioBearerConfig2;
    public RRC_SK_Counter sk_Counter;
    public RRC_RRCReconfiguration_v1560_IEs__nonCriticalExtension nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "mrdc-SecondaryCellGroupConfig","radioBearerConfig2","sk-Counter","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "mrdc_SecondaryCellGroupConfig","radioBearerConfig2","sk_Counter","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "RRCReconfiguration-v1560-IEs";
    }

    @Override
    public String getXmlTagName() {
        return "RRCReconfiguration-v1560-IEs";
    }

}
