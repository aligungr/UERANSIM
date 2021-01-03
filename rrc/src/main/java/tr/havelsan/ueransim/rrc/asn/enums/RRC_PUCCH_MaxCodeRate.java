/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public class RRC_PUCCH_MaxCodeRate extends AsnEnumerated {
    public static final RRC_PUCCH_MaxCodeRate ZERODOT08 = new RRC_PUCCH_MaxCodeRate(0);
    public static final RRC_PUCCH_MaxCodeRate ZERODOT15 = new RRC_PUCCH_MaxCodeRate(1);
    public static final RRC_PUCCH_MaxCodeRate ZERODOT25 = new RRC_PUCCH_MaxCodeRate(2);
    public static final RRC_PUCCH_MaxCodeRate ZERODOT35 = new RRC_PUCCH_MaxCodeRate(3);
    public static final RRC_PUCCH_MaxCodeRate ZERODOT45 = new RRC_PUCCH_MaxCodeRate(4);
    public static final RRC_PUCCH_MaxCodeRate ZERODOT60 = new RRC_PUCCH_MaxCodeRate(5);
    public static final RRC_PUCCH_MaxCodeRate ZERODOT80 = new RRC_PUCCH_MaxCodeRate(6);

    private RRC_PUCCH_MaxCodeRate(long value) {
        super(value);
    }
}

