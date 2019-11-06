package com.runsim.backend.protocols.nas.ielements;

import com.runsim.backend.protocols.core.OctetInputStream;

public abstract class InformationElement4 extends InformationElement {

    protected abstract InformationElement4 decodeIE4(OctetInputStream stream, int length);

    @Override
    public final InformationElement decodeIE(OctetInputStream stream, boolean ieiPresent) {
        if (ieiPresent) stream.readOctet();
        int length = stream.readOctetI();
        return decodeIE4(stream, length);
    }
}
