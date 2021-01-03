/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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

