package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement3;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.bits.Bit11;

public class IEMaximumNumberOfSupportedPacketFilters extends InformationElement3 {
    public Bit11 value;

    public IEMaximumNumberOfSupportedPacketFilters() {
    }

    public IEMaximumNumberOfSupportedPacketFilters(Bit11 value) {
        this.value = value;
    }

    @Override
    protected IEMaximumNumberOfSupportedPacketFilters decodeIE3(OctetInputStream stream) {
        int value = stream.readOctet2I();
        value >>= 5;

        var res = new IEMaximumNumberOfSupportedPacketFilters();
        res.value = new Bit11(value);
        return res;
    }

    @Override
    public void encodeIE3(OctetOutputStream stream) {
        stream.writeBits(value);
    }
}
