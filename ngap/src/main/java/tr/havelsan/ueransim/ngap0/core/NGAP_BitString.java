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

package tr.havelsan.ueransim.ngap0.core;

import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class NGAP_BitString extends NGAP_Value {

    public BitString value;

    public NGAP_BitString(BitString value) {
        this.value = value;
    }

    public NGAP_BitString(OctetString octetString, int bitLength) {
        this(BitString.from(octetString, bitLength));
    }

    public NGAP_BitString(OctetString octetString) {
        this(BitString.from(octetString));
    }

    public NGAP_BitString(Octet[] octets, int bitLength) {
        this(BitString.from(octets, bitLength));
    }

    public NGAP_BitString(Octet[] octets) {
        this(BitString.from(octets));
    }

    public NGAP_BitString(byte[] octets, int bitLength) {
        this(BitString.from(octets, bitLength));
    }

    public NGAP_BitString(byte[] octets) {
        this(BitString.from(octets));
    }

    public NGAP_BitString(String hex, int bitLength) {
        this(BitString.fromHex(hex, bitLength));
    }

    public NGAP_BitString(String hex) {
        this(BitString.fromBits(hex));
    }

    @Override
    public String getAsnName() {
        return "BIT STRING";
    }

    @Override
    public String getXmlTagName() {
        return "BIT_STRING";
    }
}
