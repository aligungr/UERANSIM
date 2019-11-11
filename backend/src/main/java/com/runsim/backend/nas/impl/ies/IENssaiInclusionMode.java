package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement1;
import com.runsim.backend.nas.impl.enums.ENssaiInclusionMode;
import com.runsim.backend.utils.bits.Bit4;

public class IENssaiInclusionMode extends InformationElement1 {
    public ENssaiInclusionMode nssaiInclusionMode;

    @Override
    public IENssaiInclusionMode decodeIE1(Bit4 value) {
        var res = new IENssaiInclusionMode();
        res.nssaiInclusionMode = ENssaiInclusionMode.fromValue(value.intValue());
        return res;
    }

    @Override
    public int encodeIE1() {
        return nssaiInclusionMode.value;
    }
}
