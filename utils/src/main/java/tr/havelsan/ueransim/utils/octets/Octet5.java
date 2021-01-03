/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.utils.octets;

import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.bits.Bit;

/**
 * Represents 5-octet or 40-bit unsigned integer
 */
public final class Octet5 extends OctetN {

    public Octet5() {
        this(0);
    }

    public Octet5(long value) {
        super(value, 5);
    }

    public Octet5(String hex) {
        this(Utils.toLong(hex));
    }

    @Override
    public final Octet5 setBit(int index, int bit) {
        return new Octet5(super.setBit(index, bit).longValue());
    }

    @Override
    public final Octet5 setBit(int index, Bit bit) {
        return new Octet5(super.setBit(index, bit).longValue());
    }

    @Override
    public final Octet5 setBitRange(int start, int end, long value) {
        return new Octet5(super.setBitRange(start, end, value).longValue());
    }
}
