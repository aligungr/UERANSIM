package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.utils.OctetOutputStream;

public class IENoIdentity extends IE5gsMobileIdentity {

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        stream.writeOctet(0xFF); // flags for no identity
    }
}
