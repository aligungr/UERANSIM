package com.runsim.backend.protocols.nas;

import com.runsim.backend.protocols.core.ProtocolValue;
import com.runsim.backend.protocols.octets.Octet;

public class HomeNetworkPKI extends ProtocolValue {
    private final Octet value;

    public HomeNetworkPKI(int value) {
        this(new Octet(value));
    }

    public HomeNetworkPKI(Octet value) {
        this.value = value;
    }

    public boolean isReserved() {
        return value.intValue == 0b11111111;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
