package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement3;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.OctetString;

public class IERand extends InformationElement3 {
    public OctetString value;

    @Override
    protected InformationElement3 decodeIE3(OctetInputStream stream) {
        var res = new IERand();
        res.value = stream.readOctetString(16);
        return res;
    }

    @Override
    public void encodeIE3(OctetOutputStream stream) {
        stream.writeOctetString(value);
    }
}
