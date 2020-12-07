/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public class RRC_NR_RS_Type extends AsnEnumerated {
    public static final RRC_NR_RS_Type SSB = new RRC_NR_RS_Type(0);
    public static final RRC_NR_RS_Type CSI_RS = new RRC_NR_RS_Type(1);

    private RRC_NR_RS_Type(long value) {
        super(value);
    }
}

