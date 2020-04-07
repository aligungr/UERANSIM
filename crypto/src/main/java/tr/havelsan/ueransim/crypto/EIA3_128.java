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
        byte[] mac = computeMac(count.toByteArray(true), bearer.intValue(), direction.boolValue(),
                message.toByteArray(), message.bitLength(), key.toByteArray());
        return BitString.from(mac);
    }

    private static native byte[] computeMac(byte[] count, int bearer, boolean direction, byte[] message, int bitLength, byte[] key);
}
