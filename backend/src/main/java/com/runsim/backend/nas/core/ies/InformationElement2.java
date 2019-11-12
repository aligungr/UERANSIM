package com.runsim.backend.nas.core.ies;

import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

/**
 * Information elements of format T with value part consisting of 0 octets
 */
public abstract class InformationElement2 extends InformationElement {

    protected abstract InformationElement2 decodeIE2();

    @Override
    public final InformationElement decodeIE(OctetInputStream stream) {
        return decodeIE2();
    }

    public abstract void encodeIE2(OctetOutputStream stream);
}
