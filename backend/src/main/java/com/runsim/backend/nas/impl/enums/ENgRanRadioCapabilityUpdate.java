package com.runsim.backend.nas.impl.enums;

import com.runsim.backend.nas.core.ProtocolEnum;

public class ENgRanRadioCapabilityUpdate extends ProtocolEnum {
    public static final ENgRanRadioCapabilityUpdate NOT_NEEDED
            = new ENgRanRadioCapabilityUpdate(0b0, "NG-RAN radio capability update not needed");
    public static final ENgRanRadioCapabilityUpdate NEEDED
            = new ENgRanRadioCapabilityUpdate(0b1, "NG-RAN radio capability update needed");

    private ENgRanRadioCapabilityUpdate(int value, String name) {
        super(value, name);
    }

    public static ENgRanRadioCapabilityUpdate fromValue(int value) {
        return fromValueGeneric(ENgRanRadioCapabilityUpdate.class, value);
    }
}
