package com.runsim.backend.nas.eap;

import com.runsim.backend.utils.octets.OctetString;

public class EapIdentity extends Eap {
    public OctetString rawData;

    public EapIdentity() {
    }

    public EapIdentity(OctetString rawData) {
        this.rawData = rawData;
    }
}
