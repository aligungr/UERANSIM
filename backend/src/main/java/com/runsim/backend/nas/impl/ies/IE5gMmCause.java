package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement3;
import com.runsim.backend.nas.impl.enums.EMmCause;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IE5gMmCause extends InformationElement3 {
    public EMmCause value;

    @Override
    protected IE5gMmCause decodeIE3(OctetInputStream stream) {
        var res = new IE5gMmCause();
        res.value = EMmCause.fromValue(stream.readOctetI());
        return res;
    }

    @Override
    public void encodeIE3(OctetOutputStream stream) {
        stream.writeOctet(value.intValue());
    }
}
