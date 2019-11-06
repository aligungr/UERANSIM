package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.octets.OctetString;

public class IEAbba extends InformationElement4 {
    // Currently only defined value is 0x0000 with length 2 (bytes)
    // (3GPP TS 33.501, 15.2.0)

    public OctetString contents;

    @Override
    protected InformationElement4 decodeIE4(OctetInputStream stream, int length) {
        var abba = new IEAbba();
        abba.contents = stream.readOctetString(length);
        return abba;
    }
}
