/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.crypto;

import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import tr.havelsan.ueransim.utils.Json;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.octets.Octet4;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.Map;

public class Test_ZUC {

    @Test
    public void test1() {
        testForFile("crypto/testdata/zuc/test1.json");
    }

    @Test
    public void test2() {
        testForFile("crypto/testdata/zuc/test2.json");
    }

    @Test
    public void test3() {
        testForFile("crypto/testdata/zuc/test3.json");
    }

    @Test
    public void test4() {
        testForFile("crypto/testdata/zuc/test4.json");
    }

    private void testForFile(String testFile) {
        Map<String, Object> json = Json.fromJson(Utils.getResourceString(testFile), new TypeToken<Map<String, Object>>() {
        }.getType());

        var key = new OctetString(json.get("key").toString().replace(" ", ""));
        var iv = new OctetString(json.get("iv").toString().replace(" ", ""));
        var length = (int) (double) (json.get("length"));
        var output = (Map<String, String>) json.get("output");

        var result = ZUC.zuc(key, iv, length);
        for (var entry : output.entrySet()) {
            int index = Integer.parseInt(entry.getKey()) - 1;
            var expected = new Octet4(entry.getValue());
            Assert.assertEquals(expected, result[index]);
        }
    }
}