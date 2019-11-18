package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement1;
import com.runsim.backend.nas.impl.enums.EServiceType;
import com.runsim.backend.utils.bits.Bit4;

public class IEServiceType extends InformationElement1 {
    public EServiceType serviceType;

    @Override
    public IEServiceType decodeIE1(Bit4 value) {
        var res = new IEServiceType();
        res.serviceType = EServiceType.fromValue(value.intValue());
        return res;
    }

    @Override
    public int encodeIE1() {
        return serviceType.intValue();
    }
}
