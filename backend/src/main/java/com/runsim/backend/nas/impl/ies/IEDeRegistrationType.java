package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement1;
import com.runsim.backend.nas.impl.enums.EDeRegistrationAccessType;
import com.runsim.backend.nas.impl.enums.EReRegistrationRequired;
import com.runsim.backend.nas.impl.enums.ESwitchOff;
import com.runsim.backend.utils.bits.Bit4;

public class IEDeRegistrationType extends InformationElement1 {
    public EDeRegistrationAccessType accessType;
    public EReRegistrationRequired reRegistrationRequired;
    public ESwitchOff switchOff;

    @Override
    public IEDeRegistrationType decodeIE1(Bit4 value) {
        var res = new IEDeRegistrationType();
        res.accessType = EDeRegistrationAccessType.fromValue(value.intValue());
        res.reRegistrationRequired = EReRegistrationRequired.fromValue(value.intValue() >> 2);
        res.switchOff = ESwitchOff.fromValue(value.intValue() >> 3);
        return res;
    }

    @Override
    public int encodeIE1() {
        int val = switchOff.intValue();
        val <<= 1;
        val |= reRegistrationRequired.intValue();
        val <<= 2;
        val |= accessType.intValue();
        return val;
    }
}
