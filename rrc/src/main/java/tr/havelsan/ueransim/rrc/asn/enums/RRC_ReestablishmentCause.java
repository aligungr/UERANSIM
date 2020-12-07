/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public class RRC_ReestablishmentCause extends AsnEnumerated {
    public static final RRC_ReestablishmentCause RECONFIGURATIONFAILURE = new RRC_ReestablishmentCause(0);
    public static final RRC_ReestablishmentCause HANDOVERFAILURE = new RRC_ReestablishmentCause(1);
    public static final RRC_ReestablishmentCause OTHERFAILURE = new RRC_ReestablishmentCause(2);
    public static final RRC_ReestablishmentCause SPARE1 = new RRC_ReestablishmentCause(3);

    private RRC_ReestablishmentCause(long value) {
        super(value);
    }
}

