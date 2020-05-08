package tr.havelsan.ueransim.crypto;

import threegpp.milenage.MilenageResult;
import threegpp.milenage.biginteger.BigIntegerBufferFactory;
import threegpp.milenage.cipher.Ciphers;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.Map;

public class Milenage {

    /**
     * Calculates OPc with given parameters.
     */
    public static OctetString calculateOpc(OctetString key, OctetString op) {
        var factory = BigIntegerBufferFactory.getInstance();
        var cipher = Ciphers.createRijndaelCipher(key.toByteArray());

        byte[] opc = threegpp.milenage.Milenage.calculateOPc(op.toByteArray(), cipher, factory);
        return new OctetString(opc);
    }

    /**
     * Calculates RES with given parameters.
     */
    public static OctetString calculateRes(OctetString key, OctetString op, OctetString rand) {
        var factory = BigIntegerBufferFactory.getInstance();
        var cipher = Ciphers.createRijndaelCipher(key.toByteArray());

        byte[] opc = threegpp.milenage.Milenage.calculateOPc(op.toByteArray(), cipher, factory);

        var milenage = new threegpp.milenage.Milenage<>(opc, cipher, factory);

        Map<MilenageResult, byte[]> result = milenage.f2f5(rand.toByteArray());

        byte[] res = result.get(MilenageResult.RES);
        return new OctetString(res);
    }

    /**
     * Calculates CK with given parameters.
     */
    public static OctetString calculateCk(OctetString key, OctetString op, OctetString rand) {
        var factory = BigIntegerBufferFactory.getInstance();
        var cipher = Ciphers.createRijndaelCipher(key.toByteArray());

        byte[] opc = threegpp.milenage.Milenage.calculateOPc(op.toByteArray(), cipher, factory);

        var milenage = new threegpp.milenage.Milenage<>(opc, cipher, factory);

        return new OctetString(milenage.f3(rand.toByteArray()));
    }

    /**
     * Calculates IK with given parameters.
     */
    public static OctetString calculateIk(OctetString key, OctetString op, OctetString rand) {
        var factory = BigIntegerBufferFactory.getInstance();
        var cipher = Ciphers.createRijndaelCipher(key.toByteArray());

        byte[] opc = threegpp.milenage.Milenage.calculateOPc(op.toByteArray(), cipher, factory);

        var milenage = new threegpp.milenage.Milenage<>(opc, cipher, factory);

        return new OctetString(milenage.f4(rand.toByteArray()));
    }

    /**
     * Calculates RES* or XRES* according to given parameters as specified in 3GPP TS 33.501
     *
     * @param key                The input key KEY shall be equal to the concatenation CK || IK of CK and IK.
     * @param servingNetworkName The serving network name shall be constructed as specified in the TS.
     * @param rand               RAND value
     * @param res                RES or XRES value
     */
    public static OctetString calculateResStar(OctetString key, String servingNetworkName, OctetString rand, OctetString res) {
        var params = new OctetString[]{KDF.encodeString(servingNetworkName), rand, res};
        var output = KDF.calculateKey(key, new Octet(0x6B), params);
        // The (X)RES* is identified with the 128 least significant bits of the output of the KDF.
        return output.substring(output.length - 16);
    }
}
