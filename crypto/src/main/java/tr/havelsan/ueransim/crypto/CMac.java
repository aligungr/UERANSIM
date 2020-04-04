package tr.havelsan.ueransim.crypto;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Pack;

public class CMac {

    public static byte[] computeMac(BlockCipher cipher, int blockSize, byte[] message, byte[] key, int macSize) {
        var subKeys = generateSubkeys(cipher, key);
        var k1 = subKeys[0];
        var k2 = subKeys[1];

        int currentOffset = 0;

        byte[] lastCode = new byte[blockSize];

        while (currentOffset < message.length) {
            int remaining = message.length - currentOffset;

            byte[] block = new byte[blockSize];

            // last block
            if (remaining <= blockSize) {
                if (remaining == blockSize) {
                    System.arraycopy(message, currentOffset, block, 0, block.length);
                    xor(block, k1);
                } else {
                    System.arraycopy(message, currentOffset, block, 0, remaining);
                    block[remaining] = (byte) 0x80;
                    for (int i = 1; i < blockSize - remaining; i++) {
                        block[i + remaining] = 0x00;
                    }
                    xor(block, k2);
                }
            }
            // not last block
            else {
                System.arraycopy(message, currentOffset, block, 0, block.length);
            }

            xor(block, lastCode);

            var code = new byte[blockSize];
            cipher.processBlock(block, 0, code, 0);

            lastCode = code;

            currentOffset += blockSize;
        }

        byte[] result = new byte[macSize];
        System.arraycopy(lastCode, 0, result, 0, result.length);
        return result;
    }

    private static byte[][] generateSubkeys(BlockCipher cipher, byte[] key) {
        cipher.reset();
        cipher.init(true, new KeyParameter(key));

        var zeros = new byte[cipher.getBlockSize()];
        var poly = lookupPoly(cipher.getBlockSize());

        byte[] L = new byte[zeros.length];
        cipher.processBlock(zeros, 0, L, 0);
        var k1 = doubleLu(L, poly);
        var k2 = doubleLu(k1, poly);

        return new byte[][]{k1, k2};
    }

    private static byte[] lookupPoly(int blockSizeLength) {
        int xor;
        switch (blockSizeLength * 8) {
            case 64:
                xor = 0x1B;
                break;
            case 128:
                xor = 0x87;
                break;
            case 160:
                xor = 0x2D;
                break;
            case 192:
                xor = 0x87;
                break;
            case 224:
                xor = 0x309;
                break;
            case 256:
                xor = 0x425;
                break;
            case 320:
                xor = 0x1B;
                break;
            case 384:
                xor = 0x100D;
                break;
            case 448:
                xor = 0x851;
                break;
            case 512:
                xor = 0x125;
                break;
            case 768:
                xor = 0xA0011;
                break;
            case 1024:
                xor = 0x80043;
                break;
            case 2048:
                xor = 0x86001;
                break;
            default:
                throw new IllegalArgumentException("Unknown block size for CMAC: " + (blockSizeLength * 8));
        }

        return Pack.intToBigEndian(xor);
    }

    private static byte[] doubleLu(byte[] in, byte[] poly) {
        byte[] ret = new byte[in.length];
        int carry = shiftLeft(in, ret);

        // This construction is an attempt at a constant-time implementation.
        int mask = (-carry) & 0xff;
        ret[in.length - 3] ^= poly[1] & mask;
        ret[in.length - 2] ^= poly[2] & mask;
        ret[in.length - 1] ^= poly[3] & mask;

        return ret;
    }

    private static int shiftLeft(byte[] block, byte[] output) {
        int i = block.length;
        int bit = 0;
        while (--i >= 0) {
            int b = block[i] & 0xff;
            output[i] = (byte) ((b << 1) | bit);
            bit = (b >>> 7) & 1;
        }
        return bit;
    }

    private static void xor(byte[] a, byte[] b) {
        for (int i = 0; i < a.length; i++) {
            a[i] ^= b[i];
        }
    }
}
