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

package tr.havelsan.ueransim.ngap0;

import tr.havelsan.ueransim.ngap0.core.NGAP_Value;
import tr.havelsan.ueransim.ngap0.pdu.NGAP_PDU;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class NgapEncoding {

    public static NGAP_PDU decodeAper(byte[] pdu) {
        return (NGAP_PDU) decodeAper(pdu, NgapDataUnitType.NGAP_PDU);
    }

    public static NGAP_Value decodeAper(byte[] pdu, NgapDataUnitType type) {
        return NgapXerEncoder.decode(NgapJni.aperToXer(pdu, type), NGAP_PDU.class);
    }

    public static NGAP_PDU decodeAper(OctetString pdu) {
        return decodeAper(pdu.toByteArray());
    }

    public static NGAP_Value decodeAper(OctetString pdu, NgapDataUnitType type) {
        return decodeAper(pdu.toByteArray(), type);
    }

    public static byte[] encodeAper(NGAP_PDU pdu) {
        return NgapJni.xerToAper(NgapXerEncoder.encode(pdu), NgapDataUnitType.NGAP_PDU);
    }

    public static byte[] encodeAper(NGAP_Value value, NgapDataUnitType type) {
        return NgapJni.xerToAper(NgapXerEncoder.encode(value), type);
    }
}
