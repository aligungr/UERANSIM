/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.crypto;

import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import tr.havelsan.ueransim.utils.Json;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.bits.Bit;
import tr.havelsan.ueransim.utils.bits.Bit5;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet4;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.Map;

public class Test_UEA2 {

    @Test
    public void test1() {
        testForFile("crypto/testdata/uea2/test1.json");
    }

    @Test
    public void test2() {
        testForFile("crypto/testdata/uea2/test2.json");
    }

    @Test
    public void test3() {
        testForFile("crypto/testdata/uea2/test3.json");
    }

    @Test
    public void test4() {
        testForFile("crypto/testdata/uea2/test4.json");
    }

    @Test
    public void test5() {
        testForFile("crypto/testdata/uea2/test5.json");
    }

    private void testForFile(String testFile) {
        Map<String, String> json = Json.fromJson(Utils.getResourceString(testFile), new TypeToken<Map<String, String>>() {
        }.getType());

        var count = new Octet4(json.get("count").replace(" ", ""));
        var bearer = new Bit5(Integer.parseInt(json.get("bearer"), 16));
        var direction = new Bit(Integer.parseInt(json.get("direction")));

        var messageHex = json.get("message").replace(" ", "");
        int messageBitLength = Integer.parseInt(json.get("length"));
        var message = BitString.fromHex(messageHex, messageBitLength);

        var key = new OctetString(json.get("key").replace(" ", ""));
        var expected = BitString.fromHex(json.get("result").replace(" ", ""), messageBitLength);

        var result = UEA2.uea2(count, bearer, direction, message, key);
        Assert.assertEquals(expected, result);
        Assert.assertEquals(message, UEA2.uea2(count, bearer, direction, result, key));
    }
}
