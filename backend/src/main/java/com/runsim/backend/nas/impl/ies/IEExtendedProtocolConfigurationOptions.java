package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.core.ies.InformationElement6;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IEExtendedProtocolConfigurationOptions extends InformationElement6 {
    public IEProtocolConfigurationOptions protocolConfigurationOptions;

    @Override
    protected IEExtendedProtocolConfigurationOptions decodeIE6(OctetInputStream stream, int length) {
        var res = new IEExtendedProtocolConfigurationOptions();
        res.protocolConfigurationOptions = NasDecoder.ie2346(stream, IEProtocolConfigurationOptions.class);
        return res;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        NasEncoder.ie2346(stream, protocolConfigurationOptions);
    }
}
