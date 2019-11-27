package com.runsim.backend.nas.impl.values;

import com.runsim.backend.exceptions.DecodingException;
import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.utils.OctetInputStream;

public abstract class VPartialTrackingAreaIdentityList extends NasValue {

    public static VPartialTrackingAreaIdentityList decode(OctetInputStream stream) {
        var octet = stream.readOctet();

        int numberOfElements = octet.getBitRangeI(0, 4) + 1; // WARNING: plus 1 is required
        // From spec: All other values are unused and shall be interpreted as 16, if received by the UE.
        if (numberOfElements > 16)
            numberOfElements = 16;

        int typeOfList = octet.getBitRangeI(5, 6);

        switch (typeOfList) {
            case 0b00:
                return VPartialTrackingAreaIdentityList00.decode(stream, numberOfElements);
            case 0b01:
                return VPartialTrackingAreaIdentityList01.decode(stream, numberOfElements);
            case 0b10:
                return VPartialTrackingAreaIdentityList10.decode(stream, numberOfElements);
            default:
                throw new DecodingException("invalid type of list for partical tracking area identity list");
        }
    }
}