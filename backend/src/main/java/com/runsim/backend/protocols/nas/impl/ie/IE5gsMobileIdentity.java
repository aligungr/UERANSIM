package com.runsim.backend.protocols.nas.impl.ie;

import com.runsim.backend.protocols.core.OctetInputStream;
import com.runsim.backend.protocols.exceptions.NotImplementedException;
import com.runsim.backend.protocols.nas.ielements.InformationElement6;
import com.runsim.backend.protocols.nas.impl.enums.EIdentityType;

public class IE5gsMobileIdentity extends InformationElement6 {

    @Override
    protected IE5gsMobileIdentity decodeIE6(OctetInputStream stream, int length) {
        int flags = stream.peekOctetI();

        var typeOfIdentity = EIdentityType.fromValue(flags & 0b111);

        if (typeOfIdentity.equals(EIdentityType.SUCI)) {
            return new IESuciMobileIdentity().decodeSUCI(stream, length);
        } else if (typeOfIdentity.equals(EIdentityType.IMEI)) {
            int isEven = (flags >> 3) & 0b1;
            return new IEImeiMobileIdentity().decodeIMEI(stream, length, isEven == 0);
        } else {
            throw new NotImplementedException("type of identity not implemented yet: " + typeOfIdentity.name);
        }
    }
}
