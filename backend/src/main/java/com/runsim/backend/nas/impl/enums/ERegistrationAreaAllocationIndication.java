package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class ERegistrationAreaAllocationIndication extends ProtocolEnum {
    public static final ERegistrationAreaAllocationIndication NOT_ALLOCATED
            = new ERegistrationAreaAllocationIndication(0b0, "all PLMN registration area not allocated");
    public static final ERegistrationAreaAllocationIndication ALLOCATED
            = new ERegistrationAreaAllocationIndication(0b1, "all PLMN registration area allocated");

    private ERegistrationAreaAllocationIndication(int value, String name) {
        super(value, name);
    }

    public static ERegistrationAreaAllocationIndication fromValue(int value) {
        return fromValueGeneric(ERegistrationAreaAllocationIndication.class, value);
    }
}
