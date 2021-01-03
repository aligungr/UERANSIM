/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.crypto;

import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.nio.charset.StandardCharsets;
import java.text.Normalizer;

public class KDF {

    /**
     * Calculates derived key based on given parameters as specified in 3GPP TS 33.220
     */
    public static OctetString calculateKey(OctetString key, int fc, OctetString... parameters) {
        var inputS = new OctetOutputStream(true);
        inputS.writeOctet(fc);
        for (var parameter : parameters) {
            inputS.writeOctetString(parameter);
            inputS.writeOctet2(parameter.length);
        }
        return MAC.hmacSha256(key, inputS.toOctetString());
    }

    /**
     * Calculates derived key based on given parameters as specified in 3GPP TS 33.220
     */
    public static OctetString calculateKey(OctetString key, int fc1, int fc2, OctetString... parameters) {
        var inputS = new OctetOutputStream(true);
        inputS.writeOctet(fc1);
        inputS.writeOctet(fc2);
        for (var parameter : parameters) {
            inputS.writeOctetString(parameter);
            inputS.writeOctet2(parameter.length);
        }
        return MAC.hmacSha256(key, inputS.toOctetString());
    }

    /**
     * Encodes given character string to octet string as specified in 3GPP TS 33.220
     */
    public static OctetString encodeString(String string) {
        // V16.0.0 - B.2.1.2 Character string encoding
        // A character string shall be encoded to an octet string according to UTF-8 encoding rules as specified in
        // IETF RFC 3629 [24] and apply Normalization Form KC (NFKC) as specified in [37].
        String normalized = Normalizer.normalize(string, Normalizer.Form.NFKC);
        byte[] bytes = normalized.getBytes(StandardCharsets.UTF_8);
        return new OctetString(bytes);
    }
}
