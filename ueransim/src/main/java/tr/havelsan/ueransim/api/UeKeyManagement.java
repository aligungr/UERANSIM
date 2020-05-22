package tr.havelsan.ueransim.api;

import tr.havelsan.ueransim.core.NasSecurityContext;
import tr.havelsan.ueransim.core.SimulationContext;
import tr.havelsan.ueransim.crypto.KDF;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class UeKeyManagement {
    private static final int N_NAS_enc_alg = 0x01;
    private static final int N_NAS_int_alg = 0x02;
    private static final int N_RRC_enc_alg = 0x03;
    private static final int N_RRC_int_alg = 0x04;
    private static final int N_UP_enc_alg = 0x05;
    private static final int N_UP_int_alg = 0x06;

    public static void deriveKeysSeafAmf(SimulationContext ctx) {
        var keys = ctx.nasSecurityContext.keys;
        keys.kSeaf = KDF.calculateKey(keys.kAusf, 0x6C, KDF.encodeString(ctx.ueData.ssn));
        keys.kAmf = KDF.calculateKey(keys.kSeaf, 0x6D, KDF.encodeString(ctx.ueData.supi), new OctetString("0000"));
    }

    public static void deriveNasKeys(NasSecurityContext securityContext) {
        var kdfEnc = KDF.calculateKey(securityContext.keys.kAmf, 0x69, new Octet(N_NAS_enc_alg).toOctetString(),
                new Octet(securityContext.selectedAlgorithms.ciphering.intValue()).toOctetString());

        var kdfInt = KDF.calculateKey(securityContext.keys.kAmf, 0x69, new Octet(N_NAS_int_alg).toOctetString(),
                new Octet(securityContext.selectedAlgorithms.integrity.intValue()).toOctetString());

        securityContext.keys.kNasEnc = kdfEnc.substring(16, 16);
        securityContext.keys.kNasInt = kdfInt.substring(16, 16);
    }

    public static OctetString[] calculateCkPrimeIkPrime(OctetString ck, OctetString ik, String snn, OctetString sqnXorAk) {
        var res = KDF.calculateKey(OctetString.concat(ck, ik), 0x20, KDF.encodeString(snn), sqnXorAk);
        return new OctetString[]{res.substring(0, ck.length), res.substring(ck.length)};
    }

    public static OctetString calculateKAusfFor5gAka(OctetString ck, OctetString ik, String snn, OctetString sqnXorAk) {
        return KDF.calculateKey(OctetString.concat(ck, ik), 0x6A, KDF.encodeString(snn), sqnXorAk);
    }

    /**
     * Calculates RES* according to given parameters as specified in 3GPP TS 33.501
     *
     * @param key  The input key KEY shall be equal to the concatenation CK || IK of CK and IK.
     * @param snn  The serving network name shall be constructed as specified in the TS.
     * @param rand RAND value
     * @param res  RES value
     */
    public static OctetString calculateResStar(OctetString key, String snn, OctetString rand, OctetString res) {
        var params = new OctetString[]{KDF.encodeString(snn), rand, res};
        var output = KDF.calculateKey(key, 0x6B, params);
        // The (X)RES* is identified with the 128 least significant bits of the output of the KDF.
        return output.substring(output.length - 16);
    }
}
