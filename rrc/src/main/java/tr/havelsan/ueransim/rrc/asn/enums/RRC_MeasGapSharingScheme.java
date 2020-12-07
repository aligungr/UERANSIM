/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public class RRC_MeasGapSharingScheme extends AsnEnumerated {
    public static final RRC_MeasGapSharingScheme SCHEME00 = new RRC_MeasGapSharingScheme(0);
    public static final RRC_MeasGapSharingScheme SCHEME01 = new RRC_MeasGapSharingScheme(1);
    public static final RRC_MeasGapSharingScheme SCHEME10 = new RRC_MeasGapSharingScheme(2);
    public static final RRC_MeasGapSharingScheme SCHEME11 = new RRC_MeasGapSharingScheme(3);

    private RRC_MeasGapSharingScheme(long value) {
        super(value);
    }
}

