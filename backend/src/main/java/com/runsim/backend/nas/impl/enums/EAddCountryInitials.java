package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class EAddCountryInitials extends ProtocolEnum {
    public static final EAddCountryInitials SHOULD_NOT_ADD
            = new EAddCountryInitials(0b0, "The MS should not add the letters for the Country's Initials to the text string");
    public static final EAddCountryInitials SHOULD_ADD
            = new EAddCountryInitials(0b1, "The MS should add the letters for the Country's Initials and a separator (e.g. a space) to the text string");

    private EAddCountryInitials(int value, String name) {
        super(value, name);
    }

    public static EAddCountryInitials fromValue(int value) {
        return fromValueGeneric(EAddCountryInitials.class, value);
    }
}
