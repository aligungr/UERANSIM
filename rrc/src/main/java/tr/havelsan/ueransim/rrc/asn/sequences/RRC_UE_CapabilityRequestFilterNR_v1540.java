/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_UE_CapabilityRequestFilterNR_v1540 extends RRC_Sequence {

    public RRC_Integer srs_SwitchingTimeRequest;
    public RRC_UE_CapabilityRequestFilterNR_v1540__nonCriticalExtension nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "srs-SwitchingTimeRequest","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "srs_SwitchingTimeRequest","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "UE-CapabilityRequestFilterNR-v1540";
    }

    @Override
    public String getXmlTagName() {
        return "UE-CapabilityRequestFilterNR-v1540";
    }

}
