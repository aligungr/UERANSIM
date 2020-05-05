package tr.havelsan.ueransim.crypto;

import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class KDF {

    public static OctetString calculateKey(OctetString key, Octet fc, OctetString[] parameters) {
        var s = calculateS(new OctetString(new Octet[]{fc}), parameters);
        return hmacSha256(key, s);
    }

    public static OctetString calculateKey(OctetString key, Octet fc1, Octet fc2, OctetString[] parameters) {
        var s = calculateS(new OctetString(new Octet[]{fc1, fc2}), parameters);
        return hmacSha256(key, s);
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
