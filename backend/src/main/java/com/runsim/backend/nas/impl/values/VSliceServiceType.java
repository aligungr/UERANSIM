package com.runsim.backend.nas.impl.values;

import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.utils.OctetInputStream;

public class VSliceServiceType extends NasValue {
    public int value;

    @Override
    public NasValue decode(OctetInputStream stream) {
        var res = new VSliceServiceType();
        res.value = stream.readOctetI();
        return res;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
