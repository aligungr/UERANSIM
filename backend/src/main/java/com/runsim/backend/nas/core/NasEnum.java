package com.runsim.backend.nas.core;

import com.runsim.backend.nas.types.ExtendedProtocolDiscriminator;

public class NasEnum extends NasValue {
    private final int value;
    private final String name;

    protected NasEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public final int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public final boolean equals(Object obj) {
        if (!(obj instanceof ExtendedProtocolDiscriminator))
            return false;
        return ((ExtendedProtocolDiscriminator) obj).getValue() == value;
    }

    @Override
    public final int hashCode() {
        return getValue();
    }

    @Override
    public final String toString() {
        return getName();
    }
}
