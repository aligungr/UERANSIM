package com.runsim.backend.nas.core.ies;

import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

/**
 * Information elements of format V or TV with value part that has fixed length of at least one octet
 */
public abstract class InformationElement3 extends InformationElement {

    protected abstract InformationElement3 decodeIE3(OctetInputStream stream);

    @Override
    public final InformationElement decodeIE(OctetInputStream stream) {
        return decodeIE3(stream);
    }

    public abstract void encodeIE3(OctetOutputStream stream);
}
