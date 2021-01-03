/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.crypto;

import tr.havelsan.ueransim.utils.bits.Bit;
import tr.havelsan.ueransim.utils.bits.Bit5;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet4;
import tr.havelsan.ueransim.utils.octets.OctetString;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EEA2_128 {

    public static BitString encrypt(Octet4 count, Bit5 bearer, Bit direction, BitString message, OctetString key) {
        byte[] res = cipher(Cipher.ENCRYPT_MODE, key.toByteArray(), computeIV(count, bearer, direction), message.toByteArray());
        return BitString.from(res, message.bitLength());
    }

    public static BitString decrypt(Octet4 count, Bit5 bearer, Bit direction, BitString message, OctetString key) {
        byte[] res = cipher(Cipher.DECRYPT_MODE, key.toByteArray(), computeIV(count, bearer, direction), message.toByteArray());
        return BitString.from(res, message.bitLength());
    }

    private static byte[] cipher(int mode, byte[] key, byte[] iv, byte[] msg) {
        try {
            var cipher = Cipher.getInstance("AES/CTR/NoPadding");
            cipher.init(mode, new SecretKeySpec(key, "AES"), new IvParameterSpec(iv));
            return cipher.doFinal(msg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] computeIV(Octet4 count, Bit5 bearer, Bit direction) {
        var iv = new BitString();
        iv.clear(127);
        BitString.copy(count.toBitString(true), 0, iv, 0, 32);
        BitString.copy(BitString.reverse(bearer.toBitString()), 0, iv, 32, 5);
        iv.set(37, direction.boolValue());
        return iv.toByteArray();
    }
}
