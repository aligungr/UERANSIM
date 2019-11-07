package com.runsim.backend.nas.impl.values;

import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class VSliceServiceType extends NasValue {
    public int value;

    @Override
    public NasValue decode(OctetInputStream stream) {
        var res = new VSliceServiceType();
        res.value = stream.readOctetI();
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        throw new NotImplementedException("");
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
