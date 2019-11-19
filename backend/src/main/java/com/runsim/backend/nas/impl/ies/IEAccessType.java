package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement1;
import com.runsim.backend.nas.impl.enums.EAccessType;
import com.runsim.backend.utils.bits.Bit4;

public class IEAccessType extends InformationElement1 {
    public EAccessType accessType;

    @Override
    public IEAccessType decodeIE1(Bit4 value) {
        var res = new IEAccessType();
        res.accessType = EAccessType.fromValue(value.intValue());
        return res;
    }

    @Override
    public int encodeIE1() {
        return accessType.intValue();
    }
}
