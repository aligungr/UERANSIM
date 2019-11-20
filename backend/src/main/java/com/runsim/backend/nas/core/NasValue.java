package com.runsim.backend.nas.core;

import com.runsim.backend.utils.OctetOutputStream;

public abstract class NasValue {

    /**
     * Encodes value to given stream
     */
    public abstract void encode(OctetOutputStream stream);

    public final byte[] toByteArray() {
        var stream = new OctetOutputStream();
        encode(stream);
        return stream.toByteArray();
    }
}
