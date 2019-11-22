package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ProtocolEnum;
import com.runsim.backend.nas.core.ies.InformationElement1;
import com.runsim.backend.utils.bits.Bit4;

public class IEMicoIndication extends InformationElement1 {
    public ERegistrationAreaAllocationIndication raai;

    @Override
    public IEMicoIndication decodeIE1(Bit4 value) {
        var res = new IEMicoIndication();
        res.raai = ERegistrationAreaAllocationIndication.fromValue(value.getBitI(0));
        return res;
    }

    @Override
    public int encodeIE1() {
        return raai.intValue();
    }

    public static class ERegistrationAreaAllocationIndication extends ProtocolEnum {
        public static final ERegistrationAreaAllocationIndication NOT_ALLOCATED
                = new ERegistrationAreaAllocationIndication(0b0, "all PLMN registration area not allocated");
        public static final ERegistrationAreaAllocationIndication ALLOCATED
                = new ERegistrationAreaAllocationIndication(0b1, "all PLMN registration area allocated");

        private ERegistrationAreaAllocationIndication(int value, String name) {
            super(value, name);
        }

        public static ERegistrationAreaAllocationIndication fromValue(int value) {
            return fromValueGeneric(ERegistrationAreaAllocationIndication.class, value);
        }
    }
}
