package com.runsim.backend.protocols.nas.impl.values;

import com.runsim.backend.protocols.core.OctetInputStream;
import com.runsim.backend.protocols.nas.messages.NasValue;
import com.runsim.backend.protocols.octets.Octet;

public class VHomeNetworkPki extends NasValue {
    public Octet value;

    public boolean isReserved() {
        return value.intValue == 0b11111111;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public VHomeNetworkPki decode(OctetInputStream stream) {
        var res = new VHomeNetworkPki();
        res.value = stream.readOctet();
        return res;
    }
}
