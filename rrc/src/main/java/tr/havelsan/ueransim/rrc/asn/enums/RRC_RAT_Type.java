/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public class RRC_RAT_Type extends AsnEnumerated {
    public static final RRC_RAT_Type NR = new RRC_RAT_Type(0);
    public static final RRC_RAT_Type EUTRA_NR = new RRC_RAT_Type(1);
    public static final RRC_RAT_Type EUTRA = new RRC_RAT_Type(2);
    public static final RRC_RAT_Type SPARE1 = new RRC_RAT_Type(3);

    private RRC_RAT_Type(long value) {
        super(value);
    }
}

