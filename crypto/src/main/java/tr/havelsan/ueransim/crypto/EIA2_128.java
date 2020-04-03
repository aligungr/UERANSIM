package tr.havelsan.ueransim.crypto;

import com.google.gson.reflect.TypeToken;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.macs.CMac;
import tr.havelsan.ueransim.utils.Json;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.bits.Bit;
import tr.havelsan.ueransim.utils.bits.BitString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EIA2_128 {

    public static BitString computeMac(InputParams params, BitString key) {
        CMac cmac = new CMac(new AESEngine());

        // todo
        return new BitString();
    }

    static List<TestData> generateTestData() {
        var list = new ArrayList<TestData>();
        list.add(TestData.fromResource("crypto/testdata/eia2_128/test1.txt"));
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
            BitString.copy(bearer, 0, m, 32, 5);
            m.set(37, direction);
            m.clear(38, 63);
            BitString.copy(message, 0, m, 64, message.bitLength());
            return m;
        }
    }

    static class TestData {
        InputParams params;
        BitString key;
        BitString result;

        public static TestData fromResource(String testFile) {
            String json = Utils.getResourceString(testFile);

            Map<String, String> obj = Json.fromJson(json, new TypeToken<Map<String, String>>() {
            }.getType());


            // todo
            return null;
        }
    }
}
