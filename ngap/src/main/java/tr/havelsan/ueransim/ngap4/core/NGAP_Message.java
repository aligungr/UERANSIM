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

import tr.havelsan.ueransim.ngap2.NgapData;
import tr.havelsan.ueransim.ngap2.NgapMessageType;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_Criticality;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_TriggeringMessage;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_ProcedureCode;

public abstract class NGAP_Message extends NGAP_Value {

    public final NgapMessageType messageType;

    public final NGAP_TriggeringMessage triggeringMessage;
    public final NGAP_ProcedureCode procedureCode;
    public final NGAP_Criticality criticality;

    public NGAP_Message(NgapMessageType messageType) {
        this.triggeringMessage = NgapData.findTriggeringMessage(messageType);
        this.procedureCode = new NGAP_ProcedureCode(NgapData.findProcedureCode(messageType));
        this.criticality = NgapData.findMessageCriticality(messageType);
        this.messageType = messageType;
    }

    @Override
    public String getAsnName() {
        return null;
    }

    @Override
    public String getXmlTagName() {
        return null;
    }
}
