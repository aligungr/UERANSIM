package com.runsim.backend.protocols.nas.impl.ie;

import com.runsim.backend.protocols.core.OctetInputStream;
import com.runsim.backend.protocols.nas.ielements.InformationElement4;
import com.runsim.backend.protocols.nas.impl.enums.E5gsRegistrationResult;
import com.runsim.backend.protocols.nas.impl.enums.ESmsOverNasTransportAllowed;

public class IE5gRegistrationResult extends InformationElement4 {
    public ESmsOverNasTransportAllowed smsOverNasAllowed;
    public E5gsRegistrationResult registrationResult;

    @Override
    protected InformationElement4 decodeIE4(OctetInputStream stream, int length) {
        int value = stream.readOctetI();

        var res = new IE5gRegistrationResult();
        res.smsOverNasAllowed = ESmsOverNasTransportAllowed.fromValue(value >> 3 & 0b1);
        res.registrationResult = E5gsRegistrationResult.fromValue(value & 0b111);
        return res;
    }
}
