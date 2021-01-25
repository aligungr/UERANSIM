/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.crypto;

import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.params.KeyParameter;
import tr.havelsan.ueransim.utils.bits.Bit;
import tr.havelsan.ueransim.utils.bits.Bit5;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet4;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class EIA2_128 {
    private static final int BLOCK_SIZE = 16;
    private static final int MAC_SIZE = 4;
    private static final int POLY = 0x87;

    public static Octet4 computeMac(Octet4 count, Bit5 bearer, Bit direction, BitString message, OctetString key) {
        if (key.length != BLOCK_SIZE) {
            throw new IllegalArgumentException("expected key length is " + BLOCK_SIZE);
        }

        var macInput = generateMacInput(count, bearer, direction, message);
        return cmac(macInput, key);
    }

    private static BitString generateMacInput(Octet4 count, Bit5 bearer, Bit direction, BitString message) {
        if (count == null) throw new IllegalStateException("count cannot be null");
        if (bearer == null) throw new IllegalStateException("bearer cannot be null");
        if (direction == null) throw new IllegalStateException("direction cannot be null");
        if (message == null) throw new IllegalStateException("message cannot be null");

        BitString bearerBs = BitString.reverse(bearer.toBitString());
        BitString countBs = count.toBitString(true);

        BitString m = new BitString();
        BitString.copy(countBs, 0, m, 0, 32);
        BitString.copy(bearerBs, 0, m, 32, 5);
        m.set(37, direction);
        m.clear(38, 63);
        BitString.copy(message, 0, m, 64, message.bitLength());
        return m;
    }

    private static Octet4 cmac(BitString message, OctetString key) {
        /////////// Initialize cipher ///////////
        AESEngine cipher;
        {
            cipher = new AESEngine();
            cipher.reset();
            cipher.init(true, new KeyParameter(key.toByteArray()));
        }

        ///////////// Generate sub keys ///////////
        BitString subKey1, subKey2;
        {
            var zeros = new byte[BLOCK_SIZE];
            byte[] L = new byte[BLOCK_SIZE];
            cipher.processBlock(zeros, 0, L, 0);
            var k1 = doubleLu(L);
            var k2 = doubleLu(k1);

            subKey1 = BitString.from(k1);
            subKey2 = BitString.from(k2);
        }

        ///////////// Process last block ///////////
        {
            int bsize = message.bitLength() % (BLOCK_SIZE * 8);
            bsize = bsize == 0 ? BLOCK_SIZE * 8 : bsize;
            if (bsize == BLOCK_SIZE * 8) {
                // Xor with subKey1
                int offset = message.bitLength() - BLOCK_SIZE * 8;
                for (int i = 0; i < BLOCK_SIZE * 8; i++) {
                    message.set(offset + i, message.getB(offset + i) ^ subKey1.getB(i));
                }
            } else {
                // Add padding
                int padding = BLOCK_SIZE * 8 - bsize;
                int bitLength = message.bitLength();
                message.set(bitLength);
                for (int i = 1; i < padding; i++) {
                    message.clear(bitLength + i);
                }

                // Xor last block with subKey2
                int offset = message.bitLength() - BLOCK_SIZE * 8;
                for (int i = 0; i < BLOCK_SIZE * 8; i++) {
                    message.set(offset + i, message.getB(offset + i) ^ subKey2.getB(i));
                }
            }
        }

        ///////////// Perform tag calculation ///////////
        Octet4 tag;
        {
            byte[] messageData = message.toByteArray();
            int messageLength = messageData.length;

            int currentOffset = 0;
            byte[] lastCode = new byte[BLOCK_SIZE];

            while (currentOffset < messageLength) {
                for (int i = 0; i < BLOCK_SIZE; i++) {
                    messageData[currentOffset + i] ^= lastCode[i];
                }

                var code = new byte[BLOCK_SIZE];
                cipher.processBlock(messageData, currentOffset, code, 0);
                lastCode = code;

                currentOffset += BLOCK_SIZE;
            }

            byte[] result = new byte[MAC_SIZE];
            System.arraycopy(lastCode, 0, result, 0, result.length);
            tag = new Octet4(result[0] & 0xFF, result[1] & 0xFF, result[2] & 0xFF, result[3] & 0xFF);
        }

        return tag;
    }

    private static byte[] doubleLu(byte[] in) {
        byte[] ret = new byte[in.length];
        int carry = shiftLeft(in, ret);

        int mask = (-carry) & 0xff;
        ret[in.length - 3] ^= ((POLY >> 16) & 0xFF) & mask;
        ret[in.length - 2] ^= ((POLY >> 8) & 0xFF) & mask;
        ret[in.length - 1] ^= (POLY & 0xFF) & mask;

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
}
