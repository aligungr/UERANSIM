package com.runsim.backend.nas.impl.values;

import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.bits.BitN;

public class VAmfSetId extends NasValue {
    public BitN value;

    @Override
    public NasValue decode(OctetInputStream stream) {
        int octet0 = stream.readOctetI();
        int octet1 = stream.peekOctetI();

        var res = new VAmfSetId();
        res.value = new BitN((octet0 << 2) | (octet1 >> 6 & 0b11), 10);
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        throw new NotImplementedException("");
    }

    @Override
    public String toString() {
        return value.toBinaryString();
    }
}
