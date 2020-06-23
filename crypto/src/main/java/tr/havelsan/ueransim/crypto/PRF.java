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
 *
 * @author Ali Güngör (aligng1620@gmail.com)
 */

package tr.havelsan.ueransim.crypto;

import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class PRF {

    /**
     * Calculates PRF' as specified in RFC 5448.
     *
     * @param key          A 256-bit key
     * @param input        Arbitrary length octet string
     * @param outputLength Octet length of the output
     */
    public static OctetString calculatePrfPrime(OctetString key, OctetString input, int outputLength) {
        if (key.length != 32) {
            throw new IllegalArgumentException("256-bit key expected");
        }

        int round = outputLength / 32 + 1;
        if (round <= 0 || round > 254) {
            throw new IllegalArgumentException("invalid outputLength value");
        }

        OctetString[] T = new OctetString[round];

        for (int i = 0; i < round; i++) {
            OctetString s;
            if (i == 0) {
                s = OctetString.concat(input, new OctetString(new Octet(i + 1)));
            } else {
                s = OctetString.concat(T[i - 1], input, new OctetString(new Octet(i + 1)));
            }
            T[i] = Mac.hmacSha256(key, s);
        }

        return OctetString.concat(T);
    }
}
