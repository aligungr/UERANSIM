/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.common.enums;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;

public class EConnectionIdentifier extends ProtocolEnum {
    public static final EConnectionIdentifier THREE_3GPP_ACCESS = new EConnectionIdentifier(0x01, "3GPP Access");
    public static final EConnectionIdentifier NON_THREE_3GPP_ACCESS = new EConnectionIdentifier(0x02, "non-3GPP access");

    private EConnectionIdentifier(int value, String name) {
        super(value, name);
    }

    public static EConnectionIdentifier fromValue(int value) {
        return fromValueGeneric(EConnectionIdentifier.class, value, null);
    }
}