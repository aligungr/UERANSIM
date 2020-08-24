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

package tr.havelsan.ueransim.ngap0;

import tr.havelsan.ueransim.ngap0.core.NGAP_BaseMessage;
import tr.havelsan.ueransim.ngap0.core.NGAP_Value;
import tr.havelsan.ueransim.ngap0.pdu.NGAP_MessageChoice;
import tr.havelsan.ueransim.ngap0.pdu.NGAP_PDU;

public class Ngap {

    public static NGAP_BaseMessage getMessageFromPdu(NGAP_PDU pdu) {
        NGAP_MessageChoice choice;
        if (pdu.initiatingMessage != null) {
            choice = pdu.initiatingMessage.value;
        } else if (pdu.successfulOutcome != null) {
            choice = pdu.successfulOutcome.value;
        } else if (pdu.unsuccessfulOutcome != null) {
            choice = pdu.unsuccessfulOutcome.value;
        } else {
            return null;
        }
        return (NGAP_BaseMessage) choice.getPresentValue();
    }

    public static <T extends NGAP_Value> T deepClone(T value) {
        return (T) NgapXerEncoder.decode(NgapXerEncoder.encode(value), value.getClass());
    }

}
