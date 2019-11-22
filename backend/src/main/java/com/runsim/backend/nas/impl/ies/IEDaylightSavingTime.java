package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ProtocolEnum;
import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IEDaylightSavingTime extends InformationElement4 {
    public EDaylightSavingTime daylightSavingTime;

    @Override
    protected IEDaylightSavingTime decodeIE4(OctetInputStream stream, int length) {
        var res = new IEDaylightSavingTime();
        res.daylightSavingTime = EDaylightSavingTime.fromValue(stream.readOctetI());
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctet(daylightSavingTime.intValue());
    }

    public static class EDaylightSavingTime extends ProtocolEnum {
        public static final EDaylightSavingTime NO_ADJUSTMENT
                = new EDaylightSavingTime(0b00, "No adjustment for Daylight Saving Time");
        public static final EDaylightSavingTime PLUS_ONE
                = new EDaylightSavingTime(0b01, "+1 hour adjustment for Daylight Saving Time");
        public static final EDaylightSavingTime PLUS_TWO
                = new EDaylightSavingTime(0b10, "+2 hours adjustment for Daylight Saving Time");

        private EDaylightSavingTime(int value, String name) {
            super(value, name);
        }

        public static EDaylightSavingTime fromValue(int value) {
            return fromValueGeneric(EDaylightSavingTime.class, value);
        }
    }
}
