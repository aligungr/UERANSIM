package tr.havelsan.ueransim.crypto;

import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.octets.Octet4;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class ZUC {

    static {
        Utils.loadLibraryFromResource("libcrypto-native.so");
    }

    public static Octet4[] zuc(OctetString key, OctetString iv, int length) {
        int[] rn = zuc(key.toByteArray(), iv.toByteArray(), length);
        Octet4[] rm = new Octet4[rn.length];
        for (int i = 0; i < rn.length; i++) {
            int word = rn[i];
            int octet3 = (word >> (24)) & 0xFF;
            int octet2 = (word >> (16)) & 0xFF;
            int octet1 = (word >> (8)) & 0xFF;
            int octet0 = (word >> (0)) & 0xFF;
            rm[i] = new Octet4(octet3, octet2, octet1, octet0);
        }
        return rm;
    }

    private static native int[] zuc(byte[] key, byte[] iv, int length);
}
