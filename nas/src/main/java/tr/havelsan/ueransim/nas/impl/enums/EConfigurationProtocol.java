/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.enums;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;

public class EConfigurationProtocol extends ProtocolEnum {
    public static final EConfigurationProtocol PPP
            = new EConfigurationProtocol(0b000, "PPP for use with IP PDP type or IP PDN type");

    private EConfigurationProtocol(int value, String name) {
        super(value, name);
    }

    public static EConfigurationProtocol fromValue(int value) {
        // All values are accepted as PPP for current version of the protocol.
        return PPP;
    }
}
