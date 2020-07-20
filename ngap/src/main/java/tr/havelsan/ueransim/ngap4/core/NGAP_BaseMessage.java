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

import tr.havelsan.ueransim.nas.NasDecoder;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_AMF_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_NAS_PDU;
import tr.havelsan.ueransim.ngap4.pdu.NGAP_PDU;

public abstract class NGAP_BaseMessage extends NGAP_Sequence {

    public abstract int getPduType();

    public abstract int getCriticality();

    public abstract int getProcedureCode();

    public abstract int[] getIeId();

    public abstract int[] getIeCriticality();

    public abstract Class<? extends NGAP_Value>[] getIeTypes();

    public abstract int[] getIePresence();

    public void addProtocolIe(NGAP_Value ie) {
        // todo
        throw new RuntimeException();
    }

    public <T extends NGAP_Value> T getProtocolIe(Class<T> type) {
        // todo
        throw new RuntimeException();
    }

    public NasMessage getNasMessage() {
        return NasDecoder.nasPdu(getProtocolIe(NGAP_NAS_PDU.class).value);
    }

    public boolean isProtocolIeUsable(Class<? extends NGAP_Value> type) {
        // todo
        throw new RuntimeException();
    }

    public boolean isUeAssociated() {
        //var ies = extractProtocolIe(ngapMessage, RAN_UE_NGAP_ID.class);
        //return ies.size() > 0;
        // todo
        throw new RuntimeException();
    }

    public NGAP_PDU buildPdu() {
        // todo
        throw new RuntimeException();
    }
}
