package tr.havelsan.ueransim.crypto;

import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.bits.Bit;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet4;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class UIA2 {

    static {
        Utils.loadLibraryFromResource("libcrypto-native.so");
    }

    public static Octet4 computeMac(Octet4 count, Octet4 fresh, Bit direction, BitString message, OctetString key) {
        if (key.length != 16) {
            throw new IllegalArgumentException("expected key length is " + 16);
        }
        int mac = computeMac(count.longValue(), fresh.longValue(), direction.boolValue(),
                message.toByteArray(), message.bitLength(), key.toByteArray());
        return new Octet4(Integer.toUnsignedLong(mac));
    }

    private static native int computeMac(long count, long fresh, boolean direction, byte[] message, int bitLength, byte[] key);
}
