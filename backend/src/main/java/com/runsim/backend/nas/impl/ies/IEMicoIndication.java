package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement1;
import com.runsim.backend.nas.impl.enums.ERegistrationAreaAllocationIndication;
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
}
