package com.runsim.backend.protocols.nas.impl.ie;

import com.runsim.backend.protocols.core.OctetInputStream;
import com.runsim.backend.protocols.exceptions.InvalidValueException;
import com.runsim.backend.protocols.nas.impl.enums.ESupiFormat;

public class IESuciMobileIdentity extends IE5gsMobileIdentity {

    public IESuciMobileIdentity decodeSUCI(OctetInputStream stream, int length) {
        int flags = stream.readOctetI();

        var supiFormat = ESupiFormat.fromValue((flags >> 4) & 0b111);

        if (supiFormat.equals(ESupiFormat.IMSI))
            return new IEImsiMobileIdentity().decodeIMSI(stream, length - 1);
        if (supiFormat.equals(ESupiFormat.NETWORK_SPECIFIC_IDENTIFIER))
            return new IENsaMobileIdentity().decodeNSA();
        throw new InvalidValueException(ESupiFormat.class);
    }
}
