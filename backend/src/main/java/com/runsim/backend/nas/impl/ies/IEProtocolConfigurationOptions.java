package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.nas.impl.enums.EConfigurationProtocol;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.OctetString;

public class IEProtocolConfigurationOptions extends InformationElement4 {
    public EConfigurationProtocol configurationProtocol;
    public OctetString rawData;

    @Override
    protected IEProtocolConfigurationOptions decodeIE4(OctetInputStream stream, int length) {
        var res = new IEProtocolConfigurationOptions();
        res.configurationProtocol = EConfigurationProtocol.fromValue(stream.readOctetI());
        res.rawData = stream.readOctetString(length - 1);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctet(configurationProtocol.intValue());
        stream.writeOctetString(rawData);
    }
}
