package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.nas.impl.enums.E5gsRegistrationResult;
import com.runsim.backend.nas.impl.enums.ESmsOverNasTransportAllowed;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IE5gsRegistrationResult extends InformationElement4 {
    public ESmsOverNasTransportAllowed smsOverNasAllowed;
    public E5gsRegistrationResult registrationResult;

    @Override
    protected InformationElement4 decodeIE4(OctetInputStream stream, int length) {
        int value = stream.readOctetI();

        var res = new IE5gsRegistrationResult();
        res.smsOverNasAllowed = ESmsOverNasTransportAllowed.fromValue(value >> 3 & 0b1);
        res.registrationResult = E5gsRegistrationResult.fromValue(value & 0b111);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctet(smsOverNasAllowed.intValue() << 3 | registrationResult.intValue());
    }
}
