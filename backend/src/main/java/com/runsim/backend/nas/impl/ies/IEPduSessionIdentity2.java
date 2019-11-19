package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement3;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.Octet;

public class IEPduSessionIdentity2 extends InformationElement3 {
    public Octet value;

    @Override
    protected IEPduSessionIdentity2 decodeIE3(OctetInputStream stream) {
        var res = new IEPduSessionIdentity2();
        res.value = stream.readOctet();
        return res;
    }

    @Override
    public void encodeIE3(OctetOutputStream stream) {
        stream.writeOctet(value);
    }
}
