/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.crypto;

import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class PRF {

    /**
     * Calculates PRF' as specified in RFC 5448.
     *
     * @param key          A 256-bit key
     * @param input        Arbitrary length octet string
     * @param outputLength Octet length of the output
     */
    public static OctetString calculatePrfPrime(OctetString key, OctetString input, int outputLength) {
        if (key.length != 32) {
            throw new IllegalArgumentException("256-bit key expected");
        }

        int round = outputLength / 32 + 1;
        if (round <= 0 || round > 254) {
            throw new IllegalArgumentException("invalid outputLength value");
        }

        OctetString[] T = new OctetString[round];

        for (int i = 0; i < round; i++) {
            OctetString s;
            if (i == 0) {
                s = OctetString.concat(input, new OctetString(new Octet(i + 1)));
            } else {
                s = OctetString.concat(T[i - 1], input, new OctetString(new Octet(i + 1)));
            }
            T[i] = MAC.hmacSha256(key, s);
        }

        return OctetString.concat(T);
    }
}
