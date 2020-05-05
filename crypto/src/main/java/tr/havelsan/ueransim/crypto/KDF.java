package tr.havelsan.ueransim.crypto;

import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;

public class KDF {

    /**
     * Calculates derived key based on given parameters as specified in 3GPP TS 33.220
     */
    public static OctetString calculateKey(OctetString key, Octet fc, OctetString[] parameters) {
        return hmacSha256(key, calculateS(new OctetString(new Octet[]{fc}), parameters));
    }

    /**
     * Calculates derived key based on given parameters as specified in 3GPP TS 33.220
     */
    public static OctetString calculateKey(OctetString key, Octet fc1, Octet fc2, OctetString[] parameters) {
        return hmacSha256(key, calculateS(new OctetString(new Octet[]{fc1, fc2}), parameters));
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

    private static OctetString calculateS(OctetString fc, OctetString[] parameters) {
        var stream = new OctetOutputStream(true);
        stream.writeOctetString(fc);
        for (var parameter : parameters) {
            stream.writeOctet2(parameter.length);
            stream.writeOctetString(parameter);
        }
        return stream.toOctetString();
    }

    private static OctetString hmacSha256(OctetString key, OctetString s) {
        try {
            final String algorithm = "HmacSHA256";
            Mac mac = Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(key.toByteArray(), algorithm));
            return new OctetString(mac.doFinal(s.toByteArray()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
