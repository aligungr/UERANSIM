/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.crypto;

import tr.havelsan.ueransim.utils.octets.OctetString;

import javax.crypto.spec.SecretKeySpec;

public class MAC {

    /**
     * Calculates the HMAC-SHA-256 with given parameters
     */
    public static OctetString hmacSha256(OctetString key, OctetString input) {
        try {
            final String algorithm = "HmacSHA256";
            javax.crypto.Mac mac = javax.crypto.Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(key.toByteArray(), algorithm));
            return new OctetString(mac.doFinal(input.toByteArray()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
