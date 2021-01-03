/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.enums;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;

public class EFollowOnRequest extends ProtocolEnum {
    public static final EFollowOnRequest NO_FOR_PENDING = new EFollowOnRequest(0b0, "No follow-on request pending");
    public static final EFollowOnRequest FOR_PENDING = new EFollowOnRequest(0b1, "Follow-on request pending");

    private EFollowOnRequest(int value, String name) {
        super(value, name);
    }

    public static EFollowOnRequest fromValue(int value) {
        return fromValueGeneric(EFollowOnRequest.class, value, null);
    }
}