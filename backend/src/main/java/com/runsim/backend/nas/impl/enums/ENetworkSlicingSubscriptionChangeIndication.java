package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class ENetworkSlicingSubscriptionChangeIndication extends ProtocolEnum {
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
