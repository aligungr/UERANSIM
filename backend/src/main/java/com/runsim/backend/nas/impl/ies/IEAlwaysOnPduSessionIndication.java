package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ProtocolEnum;
import com.runsim.backend.nas.core.ies.InformationElement1;
import com.runsim.backend.utils.bits.Bit4;

public class IEAlwaysOnPduSessionIndication extends InformationElement1 {
    public EAlwaysOnPduSessionIndication apsi;

    @Override
    public IEAlwaysOnPduSessionIndication decodeIE1(Bit4 value) {
        var res = new IEAlwaysOnPduSessionIndication();
        res.apsi = EAlwaysOnPduSessionIndication.fromValue(value.intValue());
        return res;
    }

    @Override
    public int encodeIE1() {
        return apsi.intValue();
    }

    public static class EAlwaysOnPduSessionIndication extends ProtocolEnum {
        public static final EAlwaysOnPduSessionIndication NOT_ALLOWED
                = new EAlwaysOnPduSessionIndication(0b0, "Always-on PDU session not allowed");
        public static final EAlwaysOnPduSessionIndication REQUIRED
                = new EAlwaysOnPduSessionIndication(0b1, "Always-on PDU session required");

        private EAlwaysOnPduSessionIndication(int value, String name) {
            super(value, name);
        }

        public static EAlwaysOnPduSessionIndication fromValue(int value) {
            return fromValueGeneric(EAlwaysOnPduSessionIndication.class, value);
        }
    }
}
