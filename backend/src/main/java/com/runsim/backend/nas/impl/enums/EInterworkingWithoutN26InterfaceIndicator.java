package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EInterworkingWithoutN26InterfaceIndicator extends ProtocolEnum {
    public static final EInterworkingWithoutN26InterfaceIndicator NOT_SUPPORTED
            = new EInterworkingWithoutN26InterfaceIndicator(0b0, "Interworking without N26 interface not supported");
    public static final EInterworkingWithoutN26InterfaceIndicator SUPPORTED
            = new EInterworkingWithoutN26InterfaceIndicator(0b1, "Interworking without N26 interface supported");

    private EInterworkingWithoutN26InterfaceIndicator(int value, String name) {
        super(value, name);
    }

    public static EInterworkingWithoutN26InterfaceIndicator fromValue(int value) {
        return fromValueGeneric(EInterworkingWithoutN26InterfaceIndicator.class, value);
    }
}
