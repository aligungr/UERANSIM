package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.OctetString;

public class IEAuthenticationParameterAutn extends InformationElement4 {
    public OctetString value;

    public IEAuthenticationParameterAutn() {
    }

    public IEAuthenticationParameterAutn(OctetString value) {
        this.value = value;
    }

    @Override
    protected InformationElement4 decodeIE4(OctetInputStream stream, int length) {
        var res = new IEAuthenticationParameterAutn();
        res.value = stream.readOctetString(length);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctetString(value);
    }
}
