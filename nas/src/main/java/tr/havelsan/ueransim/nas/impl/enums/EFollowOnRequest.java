/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
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