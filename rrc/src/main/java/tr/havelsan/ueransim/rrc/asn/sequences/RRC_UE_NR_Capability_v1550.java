/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_UE_NR_Capability_v1550 extends RRC_Sequence {

    public RRC_Integer reducedCP_Latency;
    public RRC_UE_NR_Capability_v1560 nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "reducedCP-Latency","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "reducedCP_Latency","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "UE-NR-Capability-v1550";
    }

    @Override
    public String getXmlTagName() {
        return "UE-NR-Capability-v1550";
    }

}
