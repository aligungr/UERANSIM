package tr.havelsan.ueransim.crypto;

import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.bits.Bit;
import tr.havelsan.ueransim.utils.bits.Bit5;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet4;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class EEA3_128 {

    static {
        Utils.loadLibraryFromResource("libcrypto-native.so");
    }

    public static BitString encrypt(Octet4 count, Bit5 bearer, Bit direction, BitString message, OctetString key) {
        if (key.length != 16) {
            throw new IllegalArgumentException("expected key length is " + 16);
        }
        byte[] res = encrypt(count.longValue(), bearer.intValue(), direction.boolValue(),
                message.toByteArray(), message.bitLength(), key.toByteArray());
        return BitString.from(res, message.bitLength());
    }

    public static BitString decrypt(Octet4 count, Bit5 bearer, Bit direction, BitString message, OctetString key) {
        if (key.length != 16) {
            throw new IllegalArgumentException("expected key length is " + 16);
        }
        byte[] res = decrypt(count.longValue(), bearer.intValue(), direction.boolValue(),
                message.toByteArray(), message.bitLength(), key.toByteArray());
        return BitString.from(res, message.bitLength());
    }

    private static native byte[] encrypt(long count, int bearer, boolean direction, byte[] message, int bitLength, byte[] key);

    private static native byte[] decrypt(long count, int bearer, boolean direction, byte[] message, int bitLength, byte[] key);
}
