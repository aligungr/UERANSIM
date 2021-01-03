/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public class RRC_TimeToTrigger extends AsnEnumerated {
    public static final RRC_TimeToTrigger MS0 = new RRC_TimeToTrigger(0);
    public static final RRC_TimeToTrigger MS40 = new RRC_TimeToTrigger(1);
    public static final RRC_TimeToTrigger MS64 = new RRC_TimeToTrigger(2);
    public static final RRC_TimeToTrigger MS80 = new RRC_TimeToTrigger(3);
    public static final RRC_TimeToTrigger MS100 = new RRC_TimeToTrigger(4);
    public static final RRC_TimeToTrigger MS128 = new RRC_TimeToTrigger(5);
    public static final RRC_TimeToTrigger MS160 = new RRC_TimeToTrigger(6);
    public static final RRC_TimeToTrigger MS256 = new RRC_TimeToTrigger(7);
    public static final RRC_TimeToTrigger MS320 = new RRC_TimeToTrigger(8);
    public static final RRC_TimeToTrigger MS480 = new RRC_TimeToTrigger(9);
    public static final RRC_TimeToTrigger MS512 = new RRC_TimeToTrigger(10);
    public static final RRC_TimeToTrigger MS640 = new RRC_TimeToTrigger(11);
    public static final RRC_TimeToTrigger MS1024 = new RRC_TimeToTrigger(12);
    public static final RRC_TimeToTrigger MS1280 = new RRC_TimeToTrigger(13);
    public static final RRC_TimeToTrigger MS2560 = new RRC_TimeToTrigger(14);
    public static final RRC_TimeToTrigger MS5120 = new RRC_TimeToTrigger(15);

    private RRC_TimeToTrigger(long value) {
        super(value);
    }
}

