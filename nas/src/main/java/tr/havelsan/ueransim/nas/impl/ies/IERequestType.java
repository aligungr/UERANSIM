/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.nas.core.ies.InformationElement1;
import tr.havelsan.ueransim.utils.bits.Bit4;

public class IERequestType extends InformationElement1 {
    public ERequestType requestType;

    public IERequestType() {
    }

    public IERequestType(ERequestType requestType) {
        this.requestType = requestType;
    }

    @Override
    public IERequestType decodeIE1(Bit4 value) {
        var res = new IERequestType();
        res.requestType = ERequestType.fromValue(value.intValue() & 0b111);
        return res;
    }

    @Override
    public int encodeIE1() {
        return requestType.intValue();
    }

    public static class ERequestType extends ProtocolEnum {
        public static final ERequestType INITIAL_REQUEST
                = new ERequestType(0b001, "initial request");
        public static final ERequestType EXISTING_PDU_SESSION
                = new ERequestType(0b010, "existing PDU session");
        public static final ERequestType INITIAL_EMERGENCY_REQUEST
                = new ERequestType(0b011, "initial emergency request");
        public static final ERequestType EXISTING_EMERGENCY_PDU_SESSION
                = new ERequestType(0b100, "existing emergency PDU session");
        public static final ERequestType MODIFICATION_REQUEST
                = new ERequestType(0b101, "modification request");
        //public static final ERequestType RESERVED
        //        = new ERequestType(0b111, "reserved");

        private ERequestType(int value, String name) {
            super(value, name);
        }

        public static ERequestType fromValue(int value) {
            return fromValueGeneric(ERequestType.class, value, null);
        }
    }
}
