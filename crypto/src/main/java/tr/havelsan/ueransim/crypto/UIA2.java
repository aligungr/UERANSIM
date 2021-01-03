/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.crypto;

import tr.havelsan.ueransim.utils.bits.Bit;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet4;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class UIA2 {

    public static Octet4 computeMac(Octet4 count, Octet4 fresh, Bit direction, BitString message, OctetString key) {
        int mac = computeMac(count.longValue(), fresh.longValue(), direction.boolValue(),
                message.toByteArray(), message.bitLength(), key.toByteArray());
        return new Octet4(Integer.toUnsignedLong(mac));
    }

    private static native int computeMac(long count, long fresh, boolean direction, byte[] message, int bitLength, byte[] key);
}
