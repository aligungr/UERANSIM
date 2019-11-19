package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement1;
import com.runsim.backend.nas.impl.enums.EPayloadContainerType;
import com.runsim.backend.utils.bits.Bit4;

public class IEPayloadContainerType extends InformationElement1 {
    public EPayloadContainerType payloadContainerType;

    @Override
    public IEPayloadContainerType decodeIE1(Bit4 value) {
        var res = new IEPayloadContainerType();
        res.payloadContainerType = EPayloadContainerType.fromValue(value.intValue());
        return res;
    }

    @Override
    public int encodeIE1() {
        return payloadContainerType.intValue();
    }
}
