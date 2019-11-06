package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.exceptions.InvalidValueException;
import com.runsim.backend.nas.impl.enums.ESupiFormat;
import com.runsim.backend.utils.OctetInputStream;

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
