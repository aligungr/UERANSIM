package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement1;
import com.runsim.backend.nas.impl.enums.ESscMode;
import com.runsim.backend.utils.bits.Bit4;

public class IESscMode extends InformationElement1 {
    public ESscMode sscMode;

    @Override
    public IESscMode decodeIE1(Bit4 value) {
        var res = new IESscMode();
        res.sscMode = ESscMode.fromValue(value.intValue());
        return res;
    }

    @Override
    public int encodeIE1() {
        return sscMode.intValue();
    }
}
