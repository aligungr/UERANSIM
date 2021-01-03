/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.enums;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;

public class EPduSessionIdentity extends ProtocolEnum {
    public static final EPduSessionIdentity NO_VAL
            = new EPduSessionIdentity(0b00000000, "No PDU session identity assigned");
    public static final EPduSessionIdentity VAL_1
            = new EPduSessionIdentity(0b00000001, "PDU session identity value 1");
    public static final EPduSessionIdentity VAL_2
            = new EPduSessionIdentity(0b00000010, "PDU session identity value 2");
    public static final EPduSessionIdentity VAL_3
            = new EPduSessionIdentity(0b00000011, "PDU session identity value 3");
    public static final EPduSessionIdentity VAL_4
            = new EPduSessionIdentity(0b00000100, "PDU session identity value 4");
    public static final EPduSessionIdentity VAL_5
            = new EPduSessionIdentity(0b00000101, "PDU session identity value 5");
    public static final EPduSessionIdentity VAL_6
            = new EPduSessionIdentity(0b00000110, "PDU session identity value 6");
    public static final EPduSessionIdentity VAL_7
            = new EPduSessionIdentity(0b00000111, "PDU session identity value 7");
    public static final EPduSessionIdentity VAL_8
            = new EPduSessionIdentity(0b00001000, "PDU session identity value 8");
    public static final EPduSessionIdentity VAL_9
            = new EPduSessionIdentity(0b00001001, "PDU session identity value 9");
    public static final EPduSessionIdentity VAL_10
            = new EPduSessionIdentity(0b00001010, "PDU session identity value 10");
    public static final EPduSessionIdentity VAL_11
            = new EPduSessionIdentity(0b00001011, "PDU session identity value 11");
    public static final EPduSessionIdentity VAL_12
            = new EPduSessionIdentity(0b00001100, "PDU session identity value 12");
    public static final EPduSessionIdentity VAL_13
            = new EPduSessionIdentity(0b00001101, "PDU session identity value 13");
    public static final EPduSessionIdentity VAL_14
            = new EPduSessionIdentity(0b00001110, "PDU session identity value 14");
    public static final EPduSessionIdentity VAL_15
            = new EPduSessionIdentity(0b00001111, "PDU session identity value 15");

    private EPduSessionIdentity(int value, String name) {
        super(value, name);
    }

    public static EPduSessionIdentity fromValue(int value) {
        return fromValueGeneric(EPduSessionIdentity.class, value, null);
    }
}
