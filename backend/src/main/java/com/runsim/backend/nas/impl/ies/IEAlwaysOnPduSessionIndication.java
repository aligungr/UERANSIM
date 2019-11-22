package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement1;
import com.runsim.backend.nas.impl.enums.EAlwaysOnPduSessionIndication;
import com.runsim.backend.utils.bits.Bit4;

public class IEAlwaysOnPduSessionIndication extends InformationElement1 {
    public EAlwaysOnPduSessionIndication apsi;

    @Override
    public IEAlwaysOnPduSessionIndication decodeIE1(Bit4 value) {
        var res = new IEAlwaysOnPduSessionIndication();
        res.apsi = EAlwaysOnPduSessionIndication.fromValue(value.intValue());
        return res;
    }

    @Override
    public int encodeIE1() {
        return apsi.intValue();
    }
}
