package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.nas.core.ies.InformationElement6;
import com.runsim.backend.nas.impl.enums.EIdentityType;
import com.runsim.backend.utils.OctetInputStream;

public class IE5gsMobileIdentity extends InformationElement6 {

    @Override
    protected final IE5gsMobileIdentity decodeIE6(OctetInputStream stream, int length) {
        int flags = stream.peekOctetI();

        var typeOfIdentity = EIdentityType.fromValue(flags & 0b111);
        int isEven = (flags >> 3) & 0b1;

        if (typeOfIdentity.equals(EIdentityType.SUCI)) {
            return new IESuciMobileIdentity().decodeSUCI(stream, length);
        } else if (typeOfIdentity.equals(EIdentityType.IMEI)) {
            return new IEImeiMobileIdentity().decodeIMEI(stream, length, isEven == 0);
        } else if (typeOfIdentity.equals(EIdentityType.GUTI)) {
            return new IE5gGutiMobileIdentity().decodeGUTI(stream, length);
        } else {
            throw new NotImplementedException("type of identity not implemented yet: " + typeOfIdentity.name);
        }
    }
}
