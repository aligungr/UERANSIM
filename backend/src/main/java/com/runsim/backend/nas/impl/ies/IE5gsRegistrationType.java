package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ProtocolEnum;
import com.runsim.backend.nas.core.ies.InformationElement1;
import com.runsim.backend.utils.bits.Bit4;

public class IE5gsRegistrationType extends InformationElement1 {
    public EFollowOnRequest followOnRequestPending;
    public ERegistrationType registrationType;

    @Override
    public InformationElement1 decodeIE1(Bit4 value) {
        int val = value.intValue();

        var res = new IE5gsRegistrationType();
        res.registrationType = ERegistrationType.fromValue(val & 0b111);
        res.followOnRequestPending = EFollowOnRequest.fromValue(val >> 3 & 0b1);
        return res;
    }

    @Override
    public int encodeIE1() {
        return followOnRequestPending.intValue() << 3 | registrationType.intValue();
    }

    public static class EFollowOnRequest extends ProtocolEnum {
        public static final EFollowOnRequest NO_FOR_PENDING
                = new EFollowOnRequest(0b0, "No follow-on request pending");
        public static final EFollowOnRequest FOR_PENDING
                = new EFollowOnRequest(0b1, "Follow-on request pending");

        private EFollowOnRequest(int value, String name) {
            super(value, name);
        }

        public static EFollowOnRequest fromValue(int value) {
            return fromValueGeneric(EFollowOnRequest.class, value, null);
        }
    }

    public static class ERegistrationType extends ProtocolEnum {
        public static final ERegistrationType INITIAL_REGISTRATION
                = new ERegistrationType(0b001, "initial registration");
        public static final ERegistrationType MOBILITY_REGISTRATION_UPDATING
                = new ERegistrationType(0b010, "mobility registration updating");
        public static final ERegistrationType PERIODIC_REGISTRATION_UPDATING
                = new ERegistrationType(0b011, "periodic registration updating");
        public static final ERegistrationType EMERGENCY_REGISTRATION
                = new ERegistrationType(0b100, "emergency registration");
        public static final ERegistrationType RESERVED
                = new ERegistrationType(0b111, "reserved");

        private ERegistrationType(int value, String name) {
            super(value, name);
        }

        public static ERegistrationType fromValue(int value) {
            return fromValueGeneric(ERegistrationType.class, value, null);
        }
    }
}
