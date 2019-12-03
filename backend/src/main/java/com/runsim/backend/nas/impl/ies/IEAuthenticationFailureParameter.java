package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.OctetString;

public class IEAuthenticationFailureParameter extends InformationElement4 {
    public OctetString rawData;

    public IEAuthenticationFailureParameter() {
    }

    public IEAuthenticationFailureParameter(OctetString rawData) {
        this.rawData = rawData;
    }

    @Override
    protected IEAuthenticationFailureParameter decodeIE4(OctetInputStream stream, int length) {
        var res = new IEAuthenticationFailureParameter();
        res.rawData = stream.readOctetString(length);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctetString(rawData);
    }
}
