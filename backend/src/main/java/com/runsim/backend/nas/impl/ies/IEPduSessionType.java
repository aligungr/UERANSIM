package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement1;
import com.runsim.backend.nas.impl.enums.EPduSessionType;
import com.runsim.backend.utils.bits.Bit4;

public class IEPduSessionType extends InformationElement1 {
    public EPduSessionType pduSessionType;

    @Override
    public IEPduSessionType decodeIE1(Bit4 value) {
        var res = new IEPduSessionType();
        res.pduSessionType = EPduSessionType.fromValue(value.intValue());
        return res;
    }

    @Override
    public int encodeIE1() {
        return pduSessionType.intValue();
    }
}
