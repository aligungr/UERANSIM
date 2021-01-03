/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public class RRC_PeriodicRNAU_TimerValue extends AsnEnumerated {
    public static final RRC_PeriodicRNAU_TimerValue MIN5 = new RRC_PeriodicRNAU_TimerValue(0);
    public static final RRC_PeriodicRNAU_TimerValue MIN10 = new RRC_PeriodicRNAU_TimerValue(1);
    public static final RRC_PeriodicRNAU_TimerValue MIN20 = new RRC_PeriodicRNAU_TimerValue(2);
    public static final RRC_PeriodicRNAU_TimerValue MIN30 = new RRC_PeriodicRNAU_TimerValue(3);
    public static final RRC_PeriodicRNAU_TimerValue MIN60 = new RRC_PeriodicRNAU_TimerValue(4);
    public static final RRC_PeriodicRNAU_TimerValue MIN120 = new RRC_PeriodicRNAU_TimerValue(5);
    public static final RRC_PeriodicRNAU_TimerValue MIN360 = new RRC_PeriodicRNAU_TimerValue(6);
    public static final RRC_PeriodicRNAU_TimerValue MIN720 = new RRC_PeriodicRNAU_TimerValue(7);

    private RRC_PeriodicRNAU_TimerValue(long value) {
        super(value);
    }
}

