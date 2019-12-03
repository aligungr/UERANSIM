package com.runsim.backend.nas.impl.values;

import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.Octet;

public class VSliceServiceType extends NasValue {
    public Octet value;

    public VSliceServiceType() {
    }

    public VSliceServiceType(Octet value) {
        this.value = value;
    }

    public VSliceServiceType(int value) {
        this(new Octet(value));
    }

    public VSliceServiceType(String hex) {
        this(new Octet(hex));
    }

    @Override
    public VSliceServiceType decode(OctetInputStream stream) {
        var res = new VSliceServiceType();
        res.value = stream.readOctet();
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        stream.writeOctet(value);
    }

    @Override
    public String toString() {
        return Integer.toString(value.intValue());
    }
}
