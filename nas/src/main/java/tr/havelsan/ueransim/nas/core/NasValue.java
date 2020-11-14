/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.core;

import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.Octet;

public abstract class NasValue {

    /**
     * Encodes value to given stream
     */
    public abstract void encode(OctetOutputStream stream);

    public abstract NasValue decode(OctetInputStream stream);

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
