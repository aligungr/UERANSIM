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

public class EEA3_128 {

    public static BitString encrypt(Octet4 count, Bit5 bearer, Bit direction, BitString message, OctetString key) {
        return eea3(count, bearer, direction, message, key);
    }

    public static BitString decrypt(Octet4 count, Bit5 bearer, Bit direction, BitString message, OctetString key) {
        return eea3(count, bearer, direction, message, key);
    }

    public static BitString eea3(Octet4 count, Bit5 bearer, Bit direction, BitString message, OctetString key) {
        byte[] res = eea3(count.longValue(), bearer.intValue(), direction.boolValue(),
                message.toByteArray(), message.bitLength(), key.toByteArray());
        return BitString.from(res, message.bitLength());
    }

    private static native byte[] eea3(long count, int bearer, boolean direction, byte[] message, int bitLength, byte[] key);
}
