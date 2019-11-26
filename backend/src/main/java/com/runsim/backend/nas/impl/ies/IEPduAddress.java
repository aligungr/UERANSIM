package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.nas.impl.enums.EPduSessionType;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.OctetString;

public class IEPduAddress extends InformationElement4 {
    public EPduSessionType sessionType;
    public OctetString pduAddressInformation;

    @Override
    protected IEPduAddress decodeIE4(OctetInputStream stream, int length) {
        var res = new IEPduAddress();
        res.sessionType = EPduSessionType.fromValue(stream.readOctetI() & 0b111);
        res.pduAddressInformation = stream.readOctetString(length - 1);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctet(sessionType.intValue());
        stream.writeOctetString(pduAddressInformation);
    }
}
