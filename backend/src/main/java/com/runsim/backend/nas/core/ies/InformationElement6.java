package com.runsim.backend.nas.core.ies;

import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

/**
 * Information elements of format LV-E or TLV-E with value part consisting of zero, one or more octets and a maximum of 65535 octets.
 */
public abstract class InformationElement6 extends InformationElement {

    protected abstract InformationElement6 decodeIE6(OctetInputStream stream, int length);

    @Override
    public final InformationElement decodeIE(OctetInputStream stream, boolean ieiPresent) {
        if (ieiPresent) stream.readOctet();
        int length = stream.readOctet2I();
        return decodeIE6(stream, length);
    }

    public abstract void encodeIE6(OctetOutputStream stream);
}
