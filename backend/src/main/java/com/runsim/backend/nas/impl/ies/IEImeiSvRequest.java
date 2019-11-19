package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement1;
import com.runsim.backend.nas.impl.enums.EImeiSvRequest;
import com.runsim.backend.utils.bits.Bit4;

public class IEImeiSvRequest extends InformationElement1 {
    public EImeiSvRequest imeiSvRequest;

    @Override
    public IEImeiSvRequest decodeIE1(Bit4 value) {
        var res = new IEImeiSvRequest();
        res.imeiSvRequest = EImeiSvRequest.fromValue(value.intValue());
        return res;
    }

    @Override
    public int encodeIE1() {
        return imeiSvRequest.intValue();
    }
}
