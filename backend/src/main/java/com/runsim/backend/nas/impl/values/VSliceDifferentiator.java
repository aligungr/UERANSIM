package com.runsim.backend.nas.impl.values;

import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.Octet3;

public class VSliceDifferentiator extends NasValue {
    public Octet3 value;

    public VSliceDifferentiator() {
    }

    public VSliceDifferentiator(Octet3 value) {
        this.value = value;
    }

    public VSliceDifferentiator(int value) {
        this(new Octet3(value));
    }

    public VSliceDifferentiator(String hex) {
        this(new Octet3(hex));
    }

    @Override
    public VSliceDifferentiator decode(OctetInputStream stream) {
        var res = new VSliceDifferentiator();
        res.value = stream.readOctet3();
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        stream.writeOctet3(value);
    }

    @Override
    public String toString() {
        return Integer.toString(value.intValue());
    }
}
