/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public class RRC_DataInactivityTimer extends AsnEnumerated {
    public static final RRC_DataInactivityTimer S1 = new RRC_DataInactivityTimer(0);
    public static final RRC_DataInactivityTimer S2 = new RRC_DataInactivityTimer(1);
    public static final RRC_DataInactivityTimer S3 = new RRC_DataInactivityTimer(2);
    public static final RRC_DataInactivityTimer S5 = new RRC_DataInactivityTimer(3);
    public static final RRC_DataInactivityTimer S7 = new RRC_DataInactivityTimer(4);
    public static final RRC_DataInactivityTimer S10 = new RRC_DataInactivityTimer(5);
    public static final RRC_DataInactivityTimer S15 = new RRC_DataInactivityTimer(6);
    public static final RRC_DataInactivityTimer S20 = new RRC_DataInactivityTimer(7);
    public static final RRC_DataInactivityTimer S40 = new RRC_DataInactivityTimer(8);
    public static final RRC_DataInactivityTimer S50 = new RRC_DataInactivityTimer(9);
    public static final RRC_DataInactivityTimer S60 = new RRC_DataInactivityTimer(10);
    public static final RRC_DataInactivityTimer S80 = new RRC_DataInactivityTimer(11);
    public static final RRC_DataInactivityTimer S100 = new RRC_DataInactivityTimer(12);
    public static final RRC_DataInactivityTimer S120 = new RRC_DataInactivityTimer(13);
    public static final RRC_DataInactivityTimer S150 = new RRC_DataInactivityTimer(14);
    public static final RRC_DataInactivityTimer S180 = new RRC_DataInactivityTimer(15);

    private RRC_DataInactivityTimer(long value) {
        super(value);
    }
}

