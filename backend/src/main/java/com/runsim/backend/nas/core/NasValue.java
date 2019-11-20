package com.runsim.backend.nas.core;

import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.Octet;

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

    public final Octet[] toOctetArray() {
        var stream = new OctetOutputStream();
        encode(stream);
        return stream.toOctetArray();
    }

    public final int[] toIntArray() {
        var stream = new OctetOutputStream();
        encode(stream);
        return stream.toIntArray();
    }
}
