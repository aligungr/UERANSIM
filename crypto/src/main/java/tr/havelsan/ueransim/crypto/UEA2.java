/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.crypto;

import tr.havelsan.ueransim.utils.bits.Bit;
import tr.havelsan.ueransim.utils.bits.Bit5;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet4;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class UEA2 {

    public static BitString uea2(Octet4 count, Bit5 bearer, Bit direction, BitString message, OctetString key) {
        byte[] res = uea2(count.longValue(), bearer.intValue(), direction.boolValue(),
                message.toByteArray(), message.bitLength(), key.toByteArray());
        return BitString.from(res, message.bitLength());
    }

    private static native byte[] uea2(long count, int bearer, boolean direction, byte[] message, int bitLength, byte[] key);
}
