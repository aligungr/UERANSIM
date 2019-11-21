package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement1;
import com.runsim.backend.nas.impl.enums.EAlwaysOnPduSessionRequested;
import com.runsim.backend.utils.bits.Bit4;

public class IEAlwaysOnPduSessionRequested extends InformationElement1 {
    public EAlwaysOnPduSessionRequested aprs;

    @Override
    public InformationElement1 decodeIE1(Bit4 value) {
        var res = new IEAlwaysOnPduSessionRequested();
        res.aprs = EAlwaysOnPduSessionRequested.fromValue(value.intValue());
        return res;
    }

    @Override
    public int encodeIE1() {
        return aprs.intValue();
    }
}
