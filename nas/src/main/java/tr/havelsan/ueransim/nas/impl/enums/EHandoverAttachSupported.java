/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.enums;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;

public class EHandoverAttachSupported extends ProtocolEnum {
    public static final EHandoverAttachSupported NOT_SUPPORTED
            = new EHandoverAttachSupported(0b0, "not supported");
    public static final EHandoverAttachSupported SUPPORTED
            = new EHandoverAttachSupported(0b1, "supported");

    private EHandoverAttachSupported(int value, String name) {
        super(value, name);
    }

    public static EHandoverAttachSupported fromValue(int value) {
        return fromValueGeneric(EHandoverAttachSupported.class, value, null);
    }
}
