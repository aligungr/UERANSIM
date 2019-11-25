package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ProtocolEnum;
import com.runsim.backend.nas.core.ies.InformationElement1;
import com.runsim.backend.utils.bits.Bit4;

public class IEAlwaysOnPduSessionRequested extends InformationElement1 {
    public EAlwaysOnPduSessionRequested aprs;

    @Override
    public InformationElement1 decodeIE1(Bit4 value) {
        var res = new IEAlwaysOnPduSessionRequested();
        res.aprs = EAlwaysOnPduSessionRequested.fromValue(value.intValue());
        return res;
    }

    @Override
    public int encodeIE1() {
        return aprs.intValue();
    }

    public static class EAlwaysOnPduSessionRequested extends ProtocolEnum {
        public static final EAlwaysOnPduSessionRequested NOT_REQUESTED
                = new EAlwaysOnPduSessionRequested(0b0, "Always-on PDU session not requested");
        public static final EAlwaysOnPduSessionRequested REQUESTED
                = new EAlwaysOnPduSessionRequested(0b1, "Always-on PDU session requested");

        private EAlwaysOnPduSessionRequested(int value, String name) {
            super(value, name);
        }

        public static EAlwaysOnPduSessionRequested fromValue(int value) {
            return fromValueGeneric(EAlwaysOnPduSessionRequested.class, value, null);
        }
    }
}
