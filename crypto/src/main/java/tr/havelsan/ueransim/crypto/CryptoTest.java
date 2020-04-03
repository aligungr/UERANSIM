package tr.havelsan.ueransim.crypto;

class CryptoTest {

    public static void main(String[] args) {
        var testDatum = EIA2_128.generateTestData();

        for (var testData : testDatum) {
            var result = EIA2_128.computeMac(testData.params, testData.key);
            if (!result.equals(testData.result)) {
                throw new RuntimeException("test failed");
            }
        }
    }
}
