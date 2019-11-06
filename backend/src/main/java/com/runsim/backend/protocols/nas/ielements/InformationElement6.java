package com.runsim.backend.protocols.nas.ielements;

import com.runsim.backend.protocols.core.OctetInputStream;

public abstract class InformationElement6 extends InformationElement {

    protected abstract InformationElement6 decodeIE6(OctetInputStream stream, int length);

    @Override
    public final InformationElement decodeIE(OctetInputStream stream, boolean ieiPresent) {
        if (ieiPresent) stream.readOctet();
        int length = stream.readOctet2I();
        return decodeIE6(stream, length);
    }
}
