package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement1;
import com.runsim.backend.nas.impl.enums.EAcknowledgement;
import com.runsim.backend.nas.impl.enums.ERegistrationRequested;
import com.runsim.backend.utils.bits.Bit4;

public class IEConfigurationUpdateIndication extends InformationElement1 {
    public EAcknowledgement ack;
    public ERegistrationRequested red;

    @Override
    public IEConfigurationUpdateIndication decodeIE1(Bit4 value) {
        var res = new IEConfigurationUpdateIndication();
        res.ack = EAcknowledgement.fromValue(value.getBitI(0));
        res.red = ERegistrationRequested.fromValue(value.getBitI(1));
        return res;
    }

    @Override
    public int encodeIE1() {
        return red.intValue() << 1 | ack.intValue();
    }
}
