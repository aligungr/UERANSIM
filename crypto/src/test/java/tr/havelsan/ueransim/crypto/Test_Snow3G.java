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
import tr.havelsan.ueransim.utils.octets.Octet4;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.Map;

public class Test_Snow3G {

    @Test
    public void test1() {
        testForFile("crypto/testdata/snow3g/test1.json");
    }

    @Test
    public void test2() {
        testForFile("crypto/testdata/snow3g/test2.json");
    }

    @Test
    public void test3() {
        testForFile("crypto/testdata/snow3g/test3.json");
    }

    @Test
    public void test4() {
        testForFile("crypto/testdata/snow3g/test4.json");
    }

    private void testForFile(String testFile) {
        Map<String, Object> json = Json.fromJson(Utils.getResourceString(testFile), new TypeToken<Map<String, Object>>() {
        }.getType());

        var key = new OctetString(json.get("key").toString().replace(" ", ""));
        var iv = new OctetString(json.get("iv").toString().replace(" ", ""));
        var length = (int) (double) (json.get("length"));
        var output = (Map<String, String>) json.get("output");

        var result = Snow3G.snow3g(key, iv, length);
        for (var entry : output.entrySet()) {
            int index = Integer.parseInt(entry.getKey()) - 1;
            var expected = new Octet4(entry.getValue());
            Assert.assertEquals(expected, result[index]);
        }
    }
}