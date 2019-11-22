package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ProtocolEnum;
import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.bits.Bit5;

public class IEGprsTimer3 extends InformationElement4 {
    public Bit5 timerValue;
    public EGprsTimerValueUnit3 unit;

    @Override
    protected IEGprsTimer3 decodeIE4(OctetInputStream stream, int length) {
        var octet = stream.readOctet();

        var res = new IEGprsTimer3();
        res.timerValue = new Bit5(octet.getBitRangeI(0, 4));
        res.unit = EGprsTimerValueUnit3.fromValue(octet.getBitRangeI(5, 7));
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        int octet = unit.intValue() & 0b111;
        octet <<= 5;
        octet |= timerValue.intValue() & 0b11111;

        stream.writeOctet(octet);
    }

    public static class EGprsTimerValueUnit3 extends ProtocolEnum {
        public static final EGprsTimerValueUnit3 MULTIPLES_OF_10MIN
                = new EGprsTimerValueUnit3(0b000, "value is incremented in multiples of 10 minutes");
        public static final EGprsTimerValueUnit3 MULTIPLES_OF_1HOUR
                = new EGprsTimerValueUnit3(0b001, "value is incremented in multiples of 1 hour");
        public static final EGprsTimerValueUnit3 MULTIPLES_OF_10HOUR
                = new EGprsTimerValueUnit3(0b010, "value is incremented in multiples of 10 hours");
        public static final EGprsTimerValueUnit3 MULTIPLES_OF_2SEC
                = new EGprsTimerValueUnit3(0b011, "value is incremented in multiples of 2 seconds");
        public static final EGprsTimerValueUnit3 MULTIPLES_OF_30HOUR
                = new EGprsTimerValueUnit3(0b100, "value is incremented in multiples of 30 seconds");
        public static final EGprsTimerValueUnit3 MULTIPLES_OF_1MIN
                = new EGprsTimerValueUnit3(0b101, "value is incremented in multiples of 1 minute");
        public static final EGprsTimerValueUnit3 MULTIPLES_OF_320HOUR
                = new EGprsTimerValueUnit3(0b110, "value is incremented in multiples of 320 hours");
        public static final EGprsTimerValueUnit3 DEACTIVATED
                = new EGprsTimerValueUnit3(0b111, "value indicates that the timer is deactivated");

        private EGprsTimerValueUnit3(int value, String name) {
            super(value, name);
        }

        public static EGprsTimerValueUnit3 fromValue(int value) {
            return fromValueGeneric(EGprsTimerValueUnit3.class, value);
        }
    }
}
