/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.values;

import tr.havelsan.ueransim.nas.core.NasValue;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.bits.Bit10;

public class VAmfSetId extends NasValue {
    public Bit10 value;

    public VAmfSetId() {
    }

    public VAmfSetId(Bit10 value) {
        this.value = value;
    }

    public VAmfSetId(int value) {
        this(new Bit10(value));
    }

    public VAmfSetId(String hex) {
        this(Utils.toInt(hex));
    }

    @Override
    public VAmfSetId decode(OctetInputStream stream) {
        int octet0 = stream.readOctetI();
        int octet1 = stream.peekOctetI();

        var res = new VAmfSetId();
        res.value = new Bit10((octet0 << 2) | (octet1 >> 6 & 0b11));
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        stream.writeBits(value);
    }

    @Override
    public String toString() {
        return value.toBinaryString();
    }
}
