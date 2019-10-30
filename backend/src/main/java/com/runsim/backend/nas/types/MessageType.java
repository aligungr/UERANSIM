package com.runsim.backend.nas.types;

import com.runsim.backend.nas.core.NasEnum;

public class MessageType extends NasEnum {
    // TODO
    public static final MessageType XXX
            = new MessageType(0b01111110, "XXX");
    public static final MessageType YYY
            = new MessageType(0b00101110, "YYY");

    private MessageType(int value, String name) {
        super(value, name);
    }

    public static MessageType fromValue(int value) {
        if (value == XXX.getValue())
            return XXX;
        else if (value == YYY.getValue())
            return YYY;
        return null;
    }
}
