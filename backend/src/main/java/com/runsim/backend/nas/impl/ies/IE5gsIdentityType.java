package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement1;
import com.runsim.backend.nas.impl.enums.EIdentityType;
import com.runsim.backend.utils.bits.Bit4;

public class IE5gsIdentityType extends InformationElement1 {
    public EIdentityType value;

    @Override
    public IE5gsIdentityType decodeIE1(Bit4 value) {
        var req = new IE5gsIdentityType();
        req.value = EIdentityType.fromValue(value.intValue() & 0b111);
        return req;
    }

    @Override
    public int encodeIE1() {
        return value.intValue();
    }
}
