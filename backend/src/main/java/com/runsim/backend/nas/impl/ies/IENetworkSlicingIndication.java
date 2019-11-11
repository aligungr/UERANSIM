package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement1;
import com.runsim.backend.nas.impl.enums.EDefaultConfiguredNssaiIndication;
import com.runsim.backend.nas.impl.enums.ENetworkSlicingSubscriptionChangeIndication;
import com.runsim.backend.utils.bits.Bit4;

public class IENetworkSlicingIndication extends InformationElement1 {
    public ENetworkSlicingSubscriptionChangeIndication nssci;
    public EDefaultConfiguredNssaiIndication dcni;

    @Override
    public IENetworkSlicingIndication decodeIE1(Bit4 value) {
        var res = new IENetworkSlicingIndication();
        res.nssci = ENetworkSlicingSubscriptionChangeIndication.fromValue(value.getBitI(0));
        res.dcni = EDefaultConfiguredNssaiIndication.fromValue(value.getBitI(1));
        return res;
    }

    @Override
    public int encodeIE1() {
        return nssci.value | (dcni.value << 1);
    }
}
