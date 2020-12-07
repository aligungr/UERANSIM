/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public class RRC_Alpha extends AsnEnumerated {
    public static final RRC_Alpha ALPHA0 = new RRC_Alpha(0);
    public static final RRC_Alpha ALPHA04 = new RRC_Alpha(1);
    public static final RRC_Alpha ALPHA05 = new RRC_Alpha(2);
    public static final RRC_Alpha ALPHA06 = new RRC_Alpha(3);
    public static final RRC_Alpha ALPHA07 = new RRC_Alpha(4);
    public static final RRC_Alpha ALPHA08 = new RRC_Alpha(5);
    public static final RRC_Alpha ALPHA09 = new RRC_Alpha(6);
    public static final RRC_Alpha ALPHA1 = new RRC_Alpha(7);

    private RRC_Alpha(long value) {
        super(value);
    }
}

