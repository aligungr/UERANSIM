package com.runsim.backend.nas.core.ies;

import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public abstract class InformationElement4 extends InformationElement {

    protected abstract InformationElement4 decodeIE4(OctetInputStream stream, int length);

    @Override
    public final InformationElement decodeIE(OctetInputStream stream, boolean ieiPresent) {
        if (ieiPresent) stream.readOctet();
        int length = stream.readOctetI();
        return decodeIE4(stream, length);
    }

    public abstract void encodeIE4(OctetOutputStream stream);
}
