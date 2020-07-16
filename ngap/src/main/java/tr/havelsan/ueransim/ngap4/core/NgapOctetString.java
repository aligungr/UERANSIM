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

package tr.havelsan.ueransim.ngap4.core;

import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class NgapOctetString extends NgapValue {

    public OctetString value;

    public NgapOctetString(OctetString value) {
        this.value = value;
    }

    public NgapOctetString(BitString value) {
        this(value.toOctetString());
    }

    public NgapOctetString(Octet[] octets) {
        this(new OctetString(octets));
    }

    public NgapOctetString(int[] octetInts) {
        this(new OctetString(octetInts));
    }

    public NgapOctetString(byte[] octetBytes) {
        this(new OctetString(octetBytes));
    }

    public NgapOctetString(String hex) {
        this(new OctetString(hex));
    }

    @Override
    protected String getAsnName() {
        return "OCTET STRING";
    }

    @Override
    protected String getXmlTagName() {
        return "OCTET_STRING";
    }
}
