package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement3;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.bits.BitN;

public class IEMaximumNumberOfSupportedPacketFilters extends InformationElement3 {
    public BitN value;

    @Override
    protected IEMaximumNumberOfSupportedPacketFilters decodeIE3(OctetInputStream stream) {
        int value = stream.readOctetI();
        value <<= 3;
        value |= stream.readOctetI() >> 5;

        var res = new IEMaximumNumberOfSupportedPacketFilters();
        res.value = new BitN(value, 11);
        return res;
    }

    @Override
    public void encodeIE3(OctetOutputStream stream) {
        stream.writeOctets(value);
    }
}
