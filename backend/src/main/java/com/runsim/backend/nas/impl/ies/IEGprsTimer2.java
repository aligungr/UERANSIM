package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.Octet;

public class IEGprsTimer2 extends InformationElement4 {
    public Octet value;

    @Override
    protected IEGprsTimer2 decodeIE4(OctetInputStream stream, int length) {
        var res = new IEGprsTimer2();
        res.value = stream.readOctet();
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctet(value);
    }
}
