package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.OctetString;

public class IEAutn extends InformationElement4 {

    public OctetString value;

    @Override
    protected InformationElement4 decodeIE4(OctetInputStream stream, int length) {
        var res = new IEAutn();
        res.value = stream.readOctetString(16);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctetString(value);
    }
}
