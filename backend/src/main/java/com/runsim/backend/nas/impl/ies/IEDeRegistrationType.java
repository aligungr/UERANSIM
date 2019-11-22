package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ProtocolEnum;
import com.runsim.backend.nas.core.ies.InformationElement1;
import com.runsim.backend.utils.bits.Bit4;

public class IEDeRegistrationType extends InformationElement1 {
    public EDeRegistrationAccessType accessType;
    public EReRegistrationRequired reRegistrationRequired;
    public ESwitchOff switchOff;

    @Override
    public IEDeRegistrationType decodeIE1(Bit4 value) {
        var res = new IEDeRegistrationType();
        res.accessType = EDeRegistrationAccessType.fromValue(value.intValue());
        res.reRegistrationRequired = EReRegistrationRequired.fromValue(value.intValue() >> 2);
        res.switchOff = ESwitchOff.fromValue(value.intValue() >> 3);
        return res;
    }

    @Override
    public int encodeIE1() {
        int val = switchOff.intValue();
        val <<= 1;
        val |= reRegistrationRequired.intValue();
        val <<= 2;
        val |= accessType.intValue();
        return val;
    }

    public static class ESwitchOff extends ProtocolEnum {
        public static final ESwitchOff NORMAL_DE_REGISTRATION
                = new ESwitchOff(0b0, "Normal de-registration");
        public static final ESwitchOff SWITCH_OFF
                = new ESwitchOff(0b1, "Switch off");

        private ESwitchOff(int value, String name) {
            super(value, name);
        }

        public static ESwitchOff fromValue(int value) {
            return fromValueGeneric(ESwitchOff.class, value);
        }
    }

    public static class EDeRegistrationAccessType extends ProtocolEnum {
        public static final EDeRegistrationAccessType THREEGPP_ACCESS
                = new EDeRegistrationAccessType(0b01, "3GPP access");
        public static final EDeRegistrationAccessType NON_THREEGPP_ACCESS
                = new EDeRegistrationAccessType(0b10, "Non-3GPP access");
        public static final EDeRegistrationAccessType THREEGPP_ACCESS_AND_NON_THREEGPP_ACCESS
                = new EDeRegistrationAccessType(0b11, "3GPP access and non-3GPP access");

        private EDeRegistrationAccessType(int value, String name) {
            super(value, name);
        }

        public static EDeRegistrationAccessType fromValue(int value) {
            return fromValueGeneric(EDeRegistrationAccessType.class, value);
        }
    }

    public static class EReRegistrationRequired extends ProtocolEnum {
        public static final EReRegistrationRequired NOT_REQUIRED
                = new EReRegistrationRequired(0b0, "re-registration not required");
        public static final EReRegistrationRequired REQUIRED
                = new EReRegistrationRequired(0b1, "re-registration required");

        private EReRegistrationRequired(int value, String name) {
            super(value, name);
        }

        public static EReRegistrationRequired fromValue(int value) {
            return fromValueGeneric(EReRegistrationRequired.class, value);
        }
    }
}
