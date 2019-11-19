package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class ECodingScheme extends ProtocolEnum {
    public static final ECodingScheme DEFAULT
            = new ECodingScheme(0b000, "Cell Broadcast data coding scheme, GSM default alphabet, language unspecified, defined in 3GPP TS 23.038");
    public static final ECodingScheme UCS2
            = new ECodingScheme(0b001, "UCS2 (16 bit)");

    private ECodingScheme(int value, String name) {
        super(value, name);
    }

    public static ECodingScheme fromValue(int value) {
        return fromValueGeneric(ECodingScheme.class, value);
    }
}
