/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public class RRC_SubcarrierSpacing extends AsnEnumerated {
    public static final RRC_SubcarrierSpacing KHZ15 = new RRC_SubcarrierSpacing(0);
    public static final RRC_SubcarrierSpacing KHZ30 = new RRC_SubcarrierSpacing(1);
    public static final RRC_SubcarrierSpacing KHZ60 = new RRC_SubcarrierSpacing(2);
    public static final RRC_SubcarrierSpacing KHZ120 = new RRC_SubcarrierSpacing(3);
    public static final RRC_SubcarrierSpacing KHZ240 = new RRC_SubcarrierSpacing(4);
    public static final RRC_SubcarrierSpacing SPARE3 = new RRC_SubcarrierSpacing(5);
    public static final RRC_SubcarrierSpacing SPARE2 = new RRC_SubcarrierSpacing(6);
    public static final RRC_SubcarrierSpacing SPARE1 = new RRC_SubcarrierSpacing(7);

    private RRC_SubcarrierSpacing(long value) {
        super(value);
    }
}

