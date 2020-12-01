/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_UE_NR_Capability_v1530 extends RRC_Sequence {

    public RRC_UE_NR_CapabilityAddXDD_Mode_v1530 fdd_Add_UE_NR_Capabilities_v1530;
    public RRC_UE_NR_CapabilityAddXDD_Mode_v1530 tdd_Add_UE_NR_Capabilities_v1530;
    public RRC_Integer dummy;
    public RRC_InterRAT_Parameters interRAT_Parameters;
    public RRC_Integer inactiveState;
    public RRC_Integer delayBudgetReporting;
    public RRC_UE_NR_Capability_v1540 nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "fdd-Add-UE-NR-Capabilities-v1530","tdd-Add-UE-NR-Capabilities-v1530","dummy","interRAT-Parameters","inactiveState","delayBudgetReporting","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "fdd_Add_UE_NR_Capabilities_v1530","tdd_Add_UE_NR_Capabilities_v1530","dummy","interRAT_Parameters","inactiveState","delayBudgetReporting","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "UE-NR-Capability-v1530";
    }

    @Override
    public String getXmlTagName() {
        return "UE-NR-Capability-v1530";
    }

}
