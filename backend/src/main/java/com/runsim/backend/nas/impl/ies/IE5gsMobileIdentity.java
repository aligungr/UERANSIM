package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.exceptions.IncorrectImplementationException;
import com.runsim.backend.exceptions.ReservedOrInvalidValueException;
import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.core.ies.InformationElement6;
import com.runsim.backend.nas.impl.enums.EIdentityType;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IE5gsMobileIdentity extends InformationElement6 {

    protected IE5gsMobileIdentity() {
    }

    @Override
    protected final IE5gsMobileIdentity decodeIE6(OctetInputStream stream, int length) {
        int flags = stream.peekOctetI();

        var typeOfIdentity = EIdentityType.fromValue(flags & 0b111);
        boolean isEven = ((flags >> 3) & 0b1) == 0;

        if (typeOfIdentity.equals(EIdentityType.SUCI)) {
            return NasDecoder.suciMobileIdentity(stream, length, isEven);
        } else if (typeOfIdentity.equals(EIdentityType.IMEI)) {
            return IEImeiMobileIdentity.decodeMobileIdentity(stream, length, isEven);
        } else if (typeOfIdentity.equals(EIdentityType.GUTI)) {
            return IE5gGutiMobileIdentity.decodeMobileIdentity(stream, length, isEven);
        } else if (typeOfIdentity.equals(EIdentityType.TMSI)) {
            return IE5gTmsiMobileIdentity.decodeMobileIdentity(stream, length, isEven);
        } else if (typeOfIdentity.equals(EIdentityType.IMEISV)) {
            return IEImeiSvMobileIdentity.decodeMobileIdentity(stream, length, isEven);
        } else if (typeOfIdentity.equals(EIdentityType.NO_IDENTITY)) {
            return IENoIdentity.decodeMobileIdentity(stream, length, isEven);
        } else {
            throw new ReservedOrInvalidValueException("Type of Identity", typeOfIdentity);
        }
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        throw new IncorrectImplementationException("sub types must override this method.");
    }
}
