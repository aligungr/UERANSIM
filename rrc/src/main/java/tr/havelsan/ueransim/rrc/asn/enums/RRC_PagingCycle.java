/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public class RRC_PagingCycle extends AsnEnumerated {
    public static final RRC_PagingCycle RF32 = new RRC_PagingCycle(0);
    public static final RRC_PagingCycle RF64 = new RRC_PagingCycle(1);
    public static final RRC_PagingCycle RF128 = new RRC_PagingCycle(2);
    public static final RRC_PagingCycle RF256 = new RRC_PagingCycle(3);

    private RRC_PagingCycle(long value) {
        super(value);
    }
}

