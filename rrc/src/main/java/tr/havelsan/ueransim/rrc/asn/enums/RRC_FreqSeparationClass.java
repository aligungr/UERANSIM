/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public class RRC_FreqSeparationClass extends AsnEnumerated {
    public static final RRC_FreqSeparationClass C1 = new RRC_FreqSeparationClass(0);
    public static final RRC_FreqSeparationClass C2 = new RRC_FreqSeparationClass(1);
    public static final RRC_FreqSeparationClass C3 = new RRC_FreqSeparationClass(2);

    private RRC_FreqSeparationClass(long value) {
        super(value);
    }
}

