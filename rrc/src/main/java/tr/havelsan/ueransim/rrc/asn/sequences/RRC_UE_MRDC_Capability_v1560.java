/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_UE_MRDC_Capability_v1560 extends RRC_Sequence {

    public RRC_OctetString receivedFilters;
    public RRC_MeasAndMobParametersMRDC_v1560 measAndMobParametersMRDC_v1560;
    public RRC_UE_MRDC_CapabilityAddXDD_Mode_v1560 fdd_Add_UE_MRDC_Capabilities_v1560;
    public RRC_UE_MRDC_CapabilityAddXDD_Mode_v1560 tdd_Add_UE_MRDC_Capabilities_v1560;
    public RRC_UE_MRDC_Capability_v1560__nonCriticalExtension nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "receivedFilters","measAndMobParametersMRDC-v1560","fdd-Add-UE-MRDC-Capabilities-v1560","tdd-Add-UE-MRDC-Capabilities-v1560","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "receivedFilters","measAndMobParametersMRDC_v1560","fdd_Add_UE_MRDC_Capabilities_v1560","tdd_Add_UE_MRDC_Capabilities_v1560","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "UE-MRDC-Capability-v1560";
    }

    @Override
    public String getXmlTagName() {
        return "UE-MRDC-Capability-v1560";
    }

}
