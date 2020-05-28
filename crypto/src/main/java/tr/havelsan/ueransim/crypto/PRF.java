package tr.havelsan.ueransim.crypto;

import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

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
            T[i] = hmacSha256(key, s);
        }

        return OctetString.concat(T);
    }

    /**
     * Calculates the HMAC-SHA-256 with given parameters
     */
    private static OctetString hmacSha256(OctetString key, OctetString input) {
        try {
            final String algorithm = "HmacSHA256";
            Mac mac = Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(key.toByteArray(), algorithm));
            return new OctetString(mac.doFinal(input.toByteArray()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
