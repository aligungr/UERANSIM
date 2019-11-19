package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement1;
import com.runsim.backend.nas.impl.enums.ERequestType;
import com.runsim.backend.utils.bits.Bit4;

public class IERequestType extends InformationElement1 {
    public ERequestType requestType;

    @Override
    public IERequestType decodeIE1(Bit4 value) {
        var res = new IERequestType();
        res.requestType = ERequestType.fromValue(value.intValue());
        return res;
    }

    @Override
    public int encodeIE1() {
        return requestType.intValue();
    }
}
