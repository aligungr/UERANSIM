package com.runsim.backend.nas.core.ies;

import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

/**
 * Information elements of format LV or TLV with value part consisting of zero, one or more octets
 */
public abstract class InformationElement4 extends InformationElement {

    protected abstract InformationElement4 decodeIE4(OctetInputStream stream, int length);

    @Override
    public final InformationElement decodeIE(OctetInputStream stream) {
        int length = stream.readOctetI();
        return decodeIE4(stream, length);
    }

    public abstract void encodeIE4(OctetOutputStream stream);
}
