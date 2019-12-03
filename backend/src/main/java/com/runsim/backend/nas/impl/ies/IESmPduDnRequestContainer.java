package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

import java.nio.charset.StandardCharsets;

public class IESmPduDnRequestContainer extends InformationElement4 {
    public String dnSpecificIdentity;

    public IESmPduDnRequestContainer() {
    }

    public IESmPduDnRequestContainer(String dnSpecificIdentity) {
        this.dnSpecificIdentity = dnSpecificIdentity;
    }

    @Override
    protected IESmPduDnRequestContainer decodeIE4(OctetInputStream stream, int length) {
        var bytes = stream.readOctetArrayB(length);
        var utf8 = new String(bytes, StandardCharsets.UTF_8);

        var res = new IESmPduDnRequestContainer();
        res.dnSpecificIdentity = utf8;
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        var bytes = dnSpecificIdentity.getBytes(StandardCharsets.UTF_8);
        stream.writeOctets(bytes);
    }
}
