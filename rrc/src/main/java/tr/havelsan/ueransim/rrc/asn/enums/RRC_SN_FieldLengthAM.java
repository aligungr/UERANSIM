/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public class RRC_SN_FieldLengthAM extends AsnEnumerated {
    public static final RRC_SN_FieldLengthAM SIZE12 = new RRC_SN_FieldLengthAM(0);
    public static final RRC_SN_FieldLengthAM SIZE18 = new RRC_SN_FieldLengthAM(1);

    private RRC_SN_FieldLengthAM(long value) {
        super(value);
    }
}

