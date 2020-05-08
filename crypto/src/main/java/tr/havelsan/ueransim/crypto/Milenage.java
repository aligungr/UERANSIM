package tr.havelsan.ueransim.crypto;

import threegpp.milenage.MilenageResult;
import threegpp.milenage.biginteger.BigIntegerBufferFactory;
import threegpp.milenage.cipher.Ciphers;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.Map;

public class Milenage {

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
}
