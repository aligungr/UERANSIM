package com.runsim.backend.nas.impl.values;

import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.utils.OctetInputStream;

public class VSliceDifferentiator extends NasValue {
    public int value;

    @Override
    public NasValue decode(OctetInputStream stream) {
        var res = new VSliceDifferentiator();
        res.value = stream.readOctet3I();
        return res;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
