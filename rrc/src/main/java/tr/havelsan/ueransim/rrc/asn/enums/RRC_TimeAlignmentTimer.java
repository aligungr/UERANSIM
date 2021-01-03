/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public class RRC_TimeAlignmentTimer extends AsnEnumerated {
    public static final RRC_TimeAlignmentTimer MS500 = new RRC_TimeAlignmentTimer(0);
    public static final RRC_TimeAlignmentTimer MS750 = new RRC_TimeAlignmentTimer(1);
    public static final RRC_TimeAlignmentTimer MS1280 = new RRC_TimeAlignmentTimer(2);
    public static final RRC_TimeAlignmentTimer MS1920 = new RRC_TimeAlignmentTimer(3);
    public static final RRC_TimeAlignmentTimer MS2560 = new RRC_TimeAlignmentTimer(4);
    public static final RRC_TimeAlignmentTimer MS5120 = new RRC_TimeAlignmentTimer(5);
    public static final RRC_TimeAlignmentTimer MS10240 = new RRC_TimeAlignmentTimer(6);
    public static final RRC_TimeAlignmentTimer INFINITY = new RRC_TimeAlignmentTimer(7);

    private RRC_TimeAlignmentTimer(long value) {
        super(value);
    }
}

