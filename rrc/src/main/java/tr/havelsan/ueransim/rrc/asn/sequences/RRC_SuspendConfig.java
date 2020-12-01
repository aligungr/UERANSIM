/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.bit_strings.RRC_I_RNTI_Value;
import tr.havelsan.ueransim.rrc.asn.bit_strings.RRC_ShortI_RNTI_Value;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_RAN_NotificationAreaInfo;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_PagingCycle;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_PeriodicRNAU_TimerValue;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_NextHopChainingCount;

public class RRC_SuspendConfig extends RRC_Sequence {

    public RRC_I_RNTI_Value fullI_RNTI;
    public RRC_ShortI_RNTI_Value shortI_RNTI;
    public RRC_PagingCycle ran_PagingCycle;
    public RRC_RAN_NotificationAreaInfo ran_NotificationAreaInfo;
    public RRC_PeriodicRNAU_TimerValue t380;
    public RRC_NextHopChainingCount nextHopChainingCount;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "fullI-RNTI","shortI-RNTI","ran-PagingCycle","ran-NotificationAreaInfo","t380","nextHopChainingCount" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "fullI_RNTI","shortI_RNTI","ran_PagingCycle","ran_NotificationAreaInfo","t380","nextHopChainingCount" };
    }

    @Override
    public String getAsnName() {
        return "SuspendConfig";
    }

    @Override
    public String getXmlTagName() {
        return "SuspendConfig";
    }

}
