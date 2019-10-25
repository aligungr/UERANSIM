package com.runsim.backend.nas.impl;

import com.runsim.backend.nas.core.BitInputStream;
import com.runsim.backend.nas.core.BitOutputStream;
import com.runsim.backend.nas.core.values.NASValue;
import com.runsim.backend.nas.core.values.SpareHalfOctetValue;

public class PlainNASMessage extends NASValue {

    ExtendedProtocolDiscriminator extendedProtocolDiscriminator;
    SecurityHeaderType securityHeaderType;
    SpareHalfOctetValue spareHalfOctetValue;

    @Override
    public void encode(BitOutputStream stream) {
        // todo
    }

    @Override
    public void decode(BitInputStream stream) {
        // todo
    }

    @Override
    public String display() {
        // todo
        return null;
    }
}
