/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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
