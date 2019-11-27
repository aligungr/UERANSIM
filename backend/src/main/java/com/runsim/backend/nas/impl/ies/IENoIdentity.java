package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IENoIdentity extends IE5gsMobileIdentity {

    public static IENoIdentity decodeMobileIdentity(OctetInputStream stream, int length, boolean isEven) {
        return new IENoIdentity();
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        stream.writeOctet(0xFF); // flags for no identity
    }
}
