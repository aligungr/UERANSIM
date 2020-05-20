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

    public static void deriveKeysAusfSeafAmf(SimulationContext ctx, OctetString key, OctetString sqnXorAk) {
        var keys = ctx.nasSecurityContext.keys;
        keys.kAusf = KDF.calculateKey(key, 0x6A, KDF.encodeString(ctx.ueData.ssn), sqnXorAk);
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
}
