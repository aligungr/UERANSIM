package tr.havelsan.ueransim.crypto;

import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;

class CryptoTest {

    public static void main(String[] args) {
        var testDatum = EIA2_128.generateTestData();

        for (var testData : testDatum) {
            var result = EIA2_128.computeMac(testData.params, testData.key);
            if (!result.equals(testData.result)) {
                Console.println(Color.RED_BOLD, "test failed: " + testData.testFile);
            } else {
                Console.println(Color.GREEN_BOLD, "test failed: " + testData.testFile);
            }
        }
    }
}
