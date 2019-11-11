package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.nas.impl.enums.EDrxValue;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IE5gsDrxParameters extends InformationElement4 {

    public EDrxValue drxValue;

    @Override
    protected IE5gsDrxParameters decodeIE4(OctetInputStream stream, int length) {
        var res = new IE5gsDrxParameters();
        res.drxValue = EDrxValue.fromValue(stream.readOctetI());
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctet(drxValue.value);
    }
}
