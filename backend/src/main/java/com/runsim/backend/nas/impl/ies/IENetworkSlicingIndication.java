package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ProtocolEnum;
import com.runsim.backend.nas.core.ies.InformationElement1;
import com.runsim.backend.nas.impl.enums.EDefaultConfiguredNssaiIndication;
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
        return nssci.intValue() | (dcni.intValue() << 1);
    }

    public static class ENetworkSlicingSubscriptionChangeIndication extends ProtocolEnum {
        public static final ENetworkSlicingSubscriptionChangeIndication NOT_CHANGED
                = new ENetworkSlicingSubscriptionChangeIndication(0b0, "Network slicing subscription not changed");
        public static final ENetworkSlicingSubscriptionChangeIndication CHANGED
                = new ENetworkSlicingSubscriptionChangeIndication(0b1, "Network slicing subscription changed");

        private ENetworkSlicingSubscriptionChangeIndication(int value, String name) {
            super(value, name);
        }

        public static ENetworkSlicingSubscriptionChangeIndication fromValue(int value) {
            return fromValueGeneric(ENetworkSlicingSubscriptionChangeIndication.class, value);
        }
    }
}
