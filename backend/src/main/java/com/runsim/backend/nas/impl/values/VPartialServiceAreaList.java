package com.runsim.backend.nas.impl.values;

import com.runsim.backend.exceptions.DecodingException;
import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.nas.core.ProtocolEnum;
import com.runsim.backend.utils.OctetInputStream;

public abstract class VPartialServiceAreaList extends NasValue {
    public EAllowedType allowedType;

    public static VPartialServiceAreaList decode(OctetInputStream stream) {
        var octet = stream.readOctet();
        int numberOfElements = octet.getBitRangeI(0, 4) + 1; // WARNING: plus 1 is required
        int typeOfList = octet.getBitRangeI(5, 6);

        VPartialServiceAreaList res;

        switch (typeOfList) {
            case 0b00:
                res = VPartialServiceAreaList00.decode(stream, numberOfElements);
                break;
            case 0b01:
                res = VPartialServiceAreaList01.decode(stream, numberOfElements);
                break;
            case 0b10:
                res = VPartialServiceAreaList10.decode(stream, numberOfElements);
                break;
            case 0b11:
                res = VPartialServiceAreaList11.decode(stream, numberOfElements);
                break;
            default:
                throw new DecodingException("invalid type of list for service area list");
        }

        res.allowedType = EAllowedType.fromValue(octet.getBitI(7));
        return res;
    }

    public static class EAllowedType extends ProtocolEnum {
        public static final EAllowedType IN_THE_ALLOWED_AREA
                = new EAllowedType(0b0, "TAIs in the list are in the allowed area");
        public static final EAllowedType IN_THE_NON_ALLOWED_AREA
                = new EAllowedType(0b1, "TAIs in the list are in the non-allowed area");

        private EAllowedType(int value, String name) {
            super(value, name);
        }

        public static EAllowedType fromValue(int value) {
            return fromValueGeneric(EAllowedType.class, value, null);
        }
    }
}
