/*
 * MIT License
 *
 * Copyright (c) 2020 ALİ GÜNGÖR
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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

public class Test_EIA2_128 {

    @Test
    public void test1() {
        testForFile("crypto/testdata/eia2_128/test1.json");
    }

    @Test
    public void test2() {
        testForFile("crypto/testdata/eia2_128/test2.json");
    }

    @Test
    public void test3() {
        testForFile("crypto/testdata/eia2_128/test3.json");
    }

    @Test
    public void test4() {
        testForFile("crypto/testdata/eia2_128/test4.json");
    }

    @Test
    public void test5() {
        testForFile("crypto/testdata/eia2_128/test5.json");
    }

    @Test
    public void test6() {
        testForFile("crypto/testdata/eia2_128/test6.json");
    }

    @Test
    public void test7() {
        testForFile("crypto/testdata/eia2_128/test7.json");
    }

    @Test
    public void test8() {
        testForFile("crypto/testdata/eia2_128/test8.json");
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
        var expected = new Octet4(json.get("result").replace(" ", ""));

        var result = EIA2_128.computeMac(count, bearer, direction, message, key);
        Assert.assertEquals(expected, result);
    }
}
