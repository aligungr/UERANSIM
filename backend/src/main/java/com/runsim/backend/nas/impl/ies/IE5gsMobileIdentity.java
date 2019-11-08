package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.core.ies.InformationElement6;
import com.runsim.backend.nas.impl.enums.EIdentityType;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public abstract class IE5gsMobileIdentity extends InformationElement6 {

    @Override
    protected final IE5gsMobileIdentity decodeIE6(OctetInputStream stream, int length) {
        int flags = stream.peekOctetI();

        var typeOfIdentity = EIdentityType.fromValue(flags & 0b111);
        int isEven = (flags >> 3) & 0b1;

        if (typeOfIdentity.equals(EIdentityType.SUCI)) {
            return NasDecoder.suciMobileIdentity(stream, length, isEven == 0);
        } else if (typeOfIdentity.equals(EIdentityType.IMEI)) {
            return new IEImeiMobileIdentity().decodeMobileIdentity(stream, length, isEven == 0);
        } else if (typeOfIdentity.equals(EIdentityType.GUTI)) {
            return new IE5gGutiMobileIdentity().decodeMobileIdentity(stream, length, isEven == 0);
        } else {
            throw new NotImplementedException("type of identity not implemented yet: " + typeOfIdentity.name);
        }
    }

    public abstract IE5gsMobileIdentity decodeMobileIdentity(OctetInputStream stream, int length, boolean isEven);

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        throw new NotImplementedException("");
    }
}
