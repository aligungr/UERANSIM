package com.runsim.backend.nas.impl.values;

import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.Utils;

public class VAmfSetId extends NasValue {
    public int value;

    @Override
    public NasValue decode(OctetInputStream stream) {
        int octet0 = stream.readOctetI();
        int octet1 = stream.peekOctetI();

        var res = new VAmfSetId();
        res.value = (octet0 << 2) | (octet1 >> 6 & 0b11);
        return res;
    }

    @Override
    public String toString() {
        return Utils.padLeft(Integer.toBinaryString(value), 10, '0');
    }
}
