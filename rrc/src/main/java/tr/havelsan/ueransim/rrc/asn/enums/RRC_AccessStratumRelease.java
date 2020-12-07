/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public class RRC_AccessStratumRelease extends AsnEnumerated {
    public static final RRC_AccessStratumRelease REL15 = new RRC_AccessStratumRelease(0);
    public static final RRC_AccessStratumRelease SPARE7 = new RRC_AccessStratumRelease(1);
    public static final RRC_AccessStratumRelease SPARE6 = new RRC_AccessStratumRelease(2);
    public static final RRC_AccessStratumRelease SPARE5 = new RRC_AccessStratumRelease(3);
    public static final RRC_AccessStratumRelease SPARE4 = new RRC_AccessStratumRelease(4);
    public static final RRC_AccessStratumRelease SPARE3 = new RRC_AccessStratumRelease(5);
    public static final RRC_AccessStratumRelease SPARE2 = new RRC_AccessStratumRelease(6);
    public static final RRC_AccessStratumRelease SPARE1 = new RRC_AccessStratumRelease(7);

    private RRC_AccessStratumRelease(long value) {
        super(value);
    }
}

