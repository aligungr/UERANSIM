/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.crypto;

import tr.havelsan.ueransim.utils.octets.Octet4;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class Snow3G {

    public static Octet4[] snow3g(OctetString key, OctetString iv, int length) {
        int[] rn = snow3g(key.toByteArray(), iv.toByteArray(), length);
        Octet4[] rm = new Octet4[rn.length];
        for (int i = 0; i < rn.length; i++) {
            rm[i] = new Octet4(Integer.toUnsignedLong(rn[i]));
        }
        return rm;
    }

    private static native int[] snow3g(byte[] key, byte[] iv, int length);
}
