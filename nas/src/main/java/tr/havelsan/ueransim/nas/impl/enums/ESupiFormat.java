/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.enums;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;

public class ESupiFormat extends ProtocolEnum {
    public static final ESupiFormat IMSI
            = new ESupiFormat(0b000, "IMSI");
    public static final ESupiFormat NETWORK_SPECIFIC_IDENTIFIER
            = new ESupiFormat(0b001, "Network Specific Identifier");

    private ESupiFormat(int value, String name) {
        super(value, name);
    }

    public static ESupiFormat fromValue(int value) {
        return fromValueGeneric(ESupiFormat.class, value, ESupiFormat.IMSI);
    }
}
