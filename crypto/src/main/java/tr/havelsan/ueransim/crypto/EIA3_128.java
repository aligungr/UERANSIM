package tr.havelsan.ueransim.crypto;

import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.bits.Bit;
import tr.havelsan.ueransim.utils.bits.Bit5;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet4;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class EIA3_128 {

    private static final int BLOCK_SIZE = 16;

    static {
        Utils.loadLibraryFromResource("libcrypto-native.so");
    }

    public static BitString computeMac(Octet4 count, Bit5 bearer, Bit direction, BitString message, OctetString key) {
        if (key.length != BLOCK_SIZE) {
            throw new IllegalArgumentException("expected key length is " + BLOCK_SIZE);
        }
        int mac = computeMac(count.longValue(), bearer.intValue(), direction.boolValue(),
                message.toByteArray(), message.bitLength(), key.toByteArray());
        int octet3 = (mac >> (24)) & 0xFF;
        int octet2 = (mac >> (16)) & 0xFF;
        int octet1 = (mac >> (8)) & 0xFF;
        int octet0 = (mac >> (0)) & 0xFF;
        return BitString.from(new Octet4(octet3, octet2, octet1, octet0).toByteArray(true));
    }

    private static native int computeMac(long count, int bearer, boolean direction, byte[] message, int bitLength, byte[] key);
}
