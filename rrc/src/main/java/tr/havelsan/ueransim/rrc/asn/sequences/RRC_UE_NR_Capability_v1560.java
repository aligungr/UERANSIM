/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_UE_NR_Capability_v1560 extends RRC_Sequence {

    public RRC_NRDC_Parameters nrdc_Parameters;
    public RRC_OctetString receivedFilters;
    public RRC_UE_NR_Capability_v1560__nonCriticalExtension nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "nrdc-Parameters","receivedFilters","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "nrdc_Parameters","receivedFilters","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "UE-NR-Capability-v1560";
    }

    @Override
    public String getXmlTagName() {
        return "UE-NR-Capability-v1560";
    }

}
