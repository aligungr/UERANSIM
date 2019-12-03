package com.runsim.backend.nas.impl.values;

import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.Utils;
import com.runsim.backend.utils.bits.Bit10;

public class VAmfSetId extends NasValue {
    public Bit10 value;

    public VAmfSetId() {
    }

    public VAmfSetId(Bit10 value) {
        this.value = value;
    }

    public VAmfSetId(int value) {
        this(new Bit10(value));
    }

    public VAmfSetId(String hex) {
        this(Utils.toInt(hex));
    }

    @Override
    public VAmfSetId decode(OctetInputStream stream) {
        int octet0 = stream.readOctetI();
        int octet1 = stream.peekOctetI();

        var res = new VAmfSetId();
        res.value = new Bit10((octet0 << 2) | (octet1 >> 6 & 0b11));
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        stream.writeBits(value);
    }

    @Override
    public String toString() {
        return value.toBinaryString();
    }
}
