package tr.havelsan.ueransim.api;

import tr.havelsan.ueransim.contexts.SimulationContext;
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

    public static void deriveKeysFor5gAka(SimulationContext ctx, OctetString ckik, OctetString sqnXorAk) {
        ctx.nasSecurityContext.keys.kAusf
                = KDF.calculateKey(ckik, 0x6A, KDF.encodeString(ctx.ueData.ssn), sqnXorAk);
        deriveKeysSeafAmf(ctx);
    }

    public static void deriveKeysForEapAkaPrime(SimulationContext ctx) {
        ctx.nasSecurityContext.keys.kAusf
                = null; // todo
        deriveKeysSeafAmf(ctx);
    }

    private static void deriveKeysSeafAmf(SimulationContext ctx) {
        var keys = ctx.nasSecurityContext.keys;
        keys.kSeaf = KDF.calculateKey(keys.kAusf, 0x6C, KDF.encodeString(ctx.ueData.ssn));
        keys.kAmf = KDF.calculateKey(keys.kSeaf, 0x6D, KDF.encodeString(ctx.ueData.supi), new OctetString("0000"));
    }

    public static void deriveNasKeys(SimulationContext ctx) {
        var keys = ctx.nasSecurityContext.keys;
        var algorithms = ctx.nasSecurityContext.selectedAlgorithms;

        var kdfEnc = KDF.calculateKey(keys.kAmf, 0x69, new Octet(N_NAS_enc_alg).toOctetString(),
                new Octet(algorithms.ciphering.intValue()).toOctetString());

        var kdfInt = KDF.calculateKey(keys.kAmf, 0x69, new Octet(N_NAS_int_alg).toOctetString(),
                new Octet(algorithms.integrity.intValue()).toOctetString());

        keys.kNasEnc = kdfEnc.substring(16, 16);
        keys.kNasInt = kdfInt.substring(16, 16);
    }

    public static OctetString[] calculateCkPrimeIkPrime(OctetString ck, OctetString ik, String ssn, OctetString sqnXorAk) {
        var res = KDF.calculateKey(OctetString.concat(ck, ik), 0x20, KDF.encodeString(ssn), sqnXorAk);
        return new OctetString[]{res.substring(0, ck.length), res.substring(ck.length)};
    }

    /**
     * Calculates RES* according to given parameters as specified in 3GPP TS 33.501
     *
     * @param key  The input key KEY shall be equal to the concatenation CK || IK of CK and IK.
     * @param ssn  The serving network name shall be constructed as specified in the TS.
     * @param rand RAND value
     * @param res  RES value
     */
    public static OctetString calculateResStar(OctetString key, String ssn, OctetString rand, OctetString res) {
        var params = new OctetString[]{KDF.encodeString(ssn), rand, res};
        var output = KDF.calculateKey(key, 0x6B, params);
        // The (X)RES* is identified with the 128 least significant bits of the output of the KDF.
        return output.substring(output.length - 16);
    }
}
