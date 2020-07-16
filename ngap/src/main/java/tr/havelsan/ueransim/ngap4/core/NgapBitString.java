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

import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class NgapBitString extends NgapValue {

    public BitString value;

    public NgapBitString(BitString value) {
        this.value = value;
    }

    public NgapBitString(OctetString octetString, int bitLength) {
        this(BitString.from(octetString, bitLength));
    }

    public NgapBitString(OctetString octetString) {
        this(BitString.from(octetString));
    }

    public NgapBitString(Octet[] octets, int bitLength) {
        this(BitString.from(octets, bitLength));
    }

    public NgapBitString(Octet[] octets) {
        this(BitString.from(octets));
    }

    public NgapBitString(byte[] octets, int bitLength) {
        this(BitString.from(octets, bitLength));
    }

    public NgapBitString(byte[] octets) {
        this(BitString.from(octets));
    }

    public NgapBitString(String hex, int bitLength) {
        this(BitString.fromHex(hex, bitLength));
    }

    public NgapBitString(String hex) {
        this(BitString.fromBits(hex));
    }

    @Override
    protected String getAsnName() {
        return "BIT STRING";
    }

    @Override
    protected String getXmlTagName() {
        return "BIT_STRING";
    }
}
