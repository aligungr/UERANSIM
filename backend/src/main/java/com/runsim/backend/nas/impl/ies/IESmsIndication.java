package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement1;
import com.runsim.backend.nas.impl.enums.ESmsAvailabilityIndication;
import com.runsim.backend.utils.bits.Bit4;

public class IESmsIndication extends InformationElement1 {
    public ESmsAvailabilityIndication sai;

    @Override
    public IESmsIndication decodeIE1(Bit4 value) {
        var res = new IESmsIndication();
        res.sai = ESmsAvailabilityIndication.fromValue(value.getBitI(0));
        return res;
    }

    @Override
    public int encodeIE1() {
        return sai.intValue();
    }
}
