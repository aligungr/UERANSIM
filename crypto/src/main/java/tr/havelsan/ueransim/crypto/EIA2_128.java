package tr.havelsan.ueransim.crypto;

import com.google.gson.reflect.TypeToken;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.params.KeyParameter;
import tr.havelsan.ueransim.utils.Json;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.bits.Bit;
import tr.havelsan.ueransim.utils.bits.BitString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EIA2_128 {
    private static final int BLOCK_SIZE = 16;
    private static final int MAC_SIZE = 4;
    private static final int POLY = 0x87;

    public static BitString computeMac(InputParams inputParams, BitString key) {
        if (key.bitLength() != BLOCK_SIZE * 8) {
            throw new IllegalArgumentException("expected key length is 32");
        }
        return cmac(inputParams.generateInput(), key);
    }

    private static BitString cmac(BitString message, BitString key) {
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
            int bsize = Math.min(message.bitLength(), BLOCK_SIZE * 8);
            if (bsize == BLOCK_SIZE * 8) {
                // Xor with subKey1
                int offset = message.bitLength() - BLOCK_SIZE * 8;
                for (int i = 0; i < BLOCK_SIZE * 8; i++) {
                    message.set(offset + 1, message.getB(offset + i) ^ subKey1.getB(i));
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
        BitString tag;
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
            tag = BitString.from(result);
        }

        return tag;
    }

    private static byte[] doubleLu(byte[] in) {
        byte[] ret = new byte[in.length];
        int carry = shiftLeft(in, ret);

        // This construction is an attempt at a constant-time implementation.
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

    static List<TestData> generateTestData() {
        String[] testFiles = {
                "crypto/testdata/eia2_128/test1.json",
                "crypto/testdata/eia2_128/test2.json",
                "crypto/testdata/eia2_128/test3.json",
                "crypto/testdata/eia2_128/test4.json",
                "crypto/testdata/eia2_128/test5.json"
        };

        var list = new ArrayList<TestData>();
        for (String testFile : testFiles) {
            list.add(TestData.fromResource(testFile));
        }
        return list;
    }

    public static class InputParams {
        private BitString count;
        private BitString bearer;
        private Bit direction;
        private BitString message;

        public BitString generateInput() {
            if (count == null) throw new IllegalStateException("count cannot be null");
            if (bearer == null) throw new IllegalStateException("bearer cannot be null");
            if (direction == null) throw new IllegalStateException("direction cannot be null");
            if (message == null) throw new IllegalStateException("message cannot be null");

            BitString m = new BitString();
            BitString.copy(count, 0, m, 0, 32);
            BitString.copy(bearer, bearer.bitLength() - 5, m, 32, 5);
            m.set(37, direction);
            m.clear(38, 63);
            BitString.copy(message, 0, m, 64, message.bitLength());
            return m;
        }
    }

    static class TestData {
        String testFile;
        InputParams params;
        BitString key;
        BitString result;

        public static TestData fromResource(String testFile) {
            Map<String, String> json = Json.fromJson(Utils.getResourceString(testFile), new TypeToken<Map<String, String>>() {
            }.getType());

            var inputParams = new InputParams();
            inputParams.count = BitString.fromHex(json.get("count").replace(" ", ""));
            inputParams.bearer = BitString.fromHex(json.get("bearer").replace(" ", ""));
            inputParams.direction = new Bit(Integer.parseInt(json.get("direction")));

            String messageHex = json.get("message").replace(" ", "");
            int messageBitLength = Integer.parseInt(json.get("length"));
            inputParams.message = BitString.fromHex(messageHex, messageBitLength);

            var testData = new TestData();
            testData.testFile = testFile;
            testData.params = inputParams;
            testData.key = BitString.fromHex(json.get("key").replace(" ", ""));
            testData.result = BitString.fromHex(json.get("result").replace(" ", ""));
            return testData;
        }
    }
}
