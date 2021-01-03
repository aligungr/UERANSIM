/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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

