package tr.havelsan.ueransim.crypto;

import com.google.gson.reflect.TypeToken;
import org.bouncycastle.crypto.engines.AESEngine;
import tr.havelsan.ueransim.utils.Json;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.bits.Bit;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EIA2_128 {

    public static BitString computeMac(InputParams inputParams, BitString key) {
        var cipher = new AESEngine();
        int blockSize = 16;
        byte[] ik = key.toByteArray();
        int macSize = 4;
        byte[] message = inputParams.generateInput().toByteArray();
        byte[] result = CMac.computeMac(cipher, blockSize, message, ik, macSize);

        return BitString.from(result);
    }

    static List<TestData> generateTestData() {
        String[] testFiles = {
                "crypto/testdata/eia2_128/test1.json",
                "crypto/testdata/eia2_128/test2.json",
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

        public InputParams() {
        }

        public InputParams setCount(BitString count) {
            this.count = count;
            return this;
        }

        public InputParams setBearer(BitString bearer) {
            this.bearer = bearer;
            return this;
        }

        public InputParams setBearer(Bit direction) {
            this.direction = direction;
            return this;
        }

        public InputParams setMessage(BitString message) {
            this.message = message;
            return this;
        }

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
            inputParams.message = BitString.fromHex(json.get("message").replace(" ", ""));

            var testData = new TestData();
            testData.testFile = testFile;
            testData.params = inputParams;
            testData.key = BitString.fromHex(json.get("key").replace(" ", ""));
            testData.result = BitString.fromHex(json.get("result").replace(" ", ""));
            return testData;
        }
    }
}
