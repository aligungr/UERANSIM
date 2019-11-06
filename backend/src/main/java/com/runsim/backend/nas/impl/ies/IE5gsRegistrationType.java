package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement1;
import com.runsim.backend.nas.impl.enums.EFollowOnRequest;
import com.runsim.backend.nas.impl.enums.ERegistrationType;
import com.runsim.backend.utils.bits.Bit4;

public class IE5gsRegistrationType extends InformationElement1 {
    public EFollowOnRequest followOnRequestPending;
    public ERegistrationType registrationType;

    @Override
    public InformationElement1 decodeIE1(Bit4 value) {
        int val = value.intValue;

        var res = new IE5gsRegistrationType();
        res.followOnRequestPending = EFollowOnRequest.fromValue(val >> 3 & 0b1);
        res.registrationType = ERegistrationType.fromValue(val & 0b111);
        return res;
    }
}
