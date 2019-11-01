package com.runsim.backend.protocols.eap;

import com.runsim.backend.protocols.OctetInputStream;
import com.runsim.backend.protocols.exceptions.NotImplementedException;

public class EAPDecoder {
    private final OctetInputStream data;

    public EAPDecoder(byte[] data) {
        this(new OctetInputStream(data));
    }

    public EAPDecoder(OctetInputStream data) {
        this.data = data;
    }

    public ExtensibleAuthenticationProtocol decodeEAP() {
        throw new NotImplementedException("");
    }
}
