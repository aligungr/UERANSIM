package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement6;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.OctetString;

public class IENasMessageContainer extends InformationElement6 {
    public OctetString value;

    @Override
    protected IENasMessageContainer decodeIE6(OctetInputStream stream, int length) {
        var res = new IENasMessageContainer();
        res.value = stream.readOctetString(length);
        return res;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        stream.writeOctetString(value);
    }
}
