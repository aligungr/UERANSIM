package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.OctetString;

public class IEAdditionalInformation extends InformationElement4 {
    public OctetString data;

    @Override
    protected IEAdditionalInformation decodeIE4(OctetInputStream stream, int length) {
        var res = new IEAdditionalInformation();
        res.data = stream.readOctetString(length);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctetString(data);
    }
}
