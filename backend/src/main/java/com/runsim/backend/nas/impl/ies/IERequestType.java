package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ProtocolEnum;
import com.runsim.backend.nas.core.ies.InformationElement1;
import com.runsim.backend.utils.bits.Bit4;

public class IERequestType extends InformationElement1 {
    public ERequestType requestType;

    @Override
    public IERequestType decodeIE1(Bit4 value) {
        var res = new IERequestType();
        res.requestType = ERequestType.fromValue(value.intValue());
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
        public static final ERequestType RESERVED
                = new ERequestType(0b111, "reserved");

        private ERequestType(int value, String name) {
            super(value, name);
        }

        public static ERequestType fromValue(int value) {
            return fromValueGeneric(ERequestType.class, value);
        }
    }
}
