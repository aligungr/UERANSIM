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

public class NGAP_OctetString extends NGAP_Value {

    public OctetString value;

    public NGAP_OctetString(OctetString value) {
        this.value = value;
    }

    public NGAP_OctetString(BitString value) {
        this(value.toOctetString());
    }

    public NGAP_OctetString(Octet[] octets) {
        this(new OctetString(octets));
    }

    public NGAP_OctetString(int[] octetInts) {
        this(new OctetString(octetInts));
    }

    public NGAP_OctetString(byte[] octetBytes) {
        this(new OctetString(octetBytes));
    }

    public NGAP_OctetString(String hex) {
        this(new OctetString(hex));
    }

    @Override
    public String getAsnName() {
        return "OCTET STRING";
    }

    @Override
    public String getXmlTagName() {
        return "OCTET_STRING";
    }
}
