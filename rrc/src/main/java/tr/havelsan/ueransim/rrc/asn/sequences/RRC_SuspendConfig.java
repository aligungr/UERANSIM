/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.bit_strings.RRC_I_RNTI_Value;
import tr.havelsan.ueransim.rrc.asn.bit_strings.RRC_ShortI_RNTI_Value;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_RAN_NotificationAreaInfo;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_PagingCycle;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_PeriodicRNAU_TimerValue;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_NextHopChainingCount;

public class RRC_SuspendConfig extends AsnSequence {
    public RRC_I_RNTI_Value fullI_RNTI; // mandatory
    public RRC_ShortI_RNTI_Value shortI_RNTI; // mandatory
    public RRC_PagingCycle ran_PagingCycle; // mandatory
    public RRC_RAN_NotificationAreaInfo ran_NotificationAreaInfo; // optional
    public RRC_PeriodicRNAU_TimerValue t380; // optional
    public RRC_NextHopChainingCount nextHopChainingCount; // mandatory
}

