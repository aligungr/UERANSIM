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

package tr.havelsan.ueransim.nas.impl.messages;

import tr.havelsan.ueransim.nas.core.IMessageBuilder;
import tr.havelsan.ueransim.nas.core.messages.PlainMmMessage;
import tr.havelsan.ueransim.nas.impl.enums.EMessageType;
import tr.havelsan.ueransim.nas.impl.ies.*;

public class UlNasTransport extends PlainMmMessage {
    public IEPayloadContainerType payloadContainerType;
    public IEPayloadContainer payloadContainer;
    public IEPduSessionIdentity2 pduSessionId;
    public IEPduSessionIdentity2 oldPduSessionId;
    public IERequestType requestType;
    public IESNssai sNssa;
    public IEDnn dnn;
    public IEAdditionalInformation additionalInformation;

    public UlNasTransport() {
        super(EMessageType.UL_NAS_TRANSPORT);
    }

    public UlNasTransport(IEPayloadContainerType payloadContainerType, IEPayloadContainer payloadContainer, IEPduSessionIdentity2 pduSessionId, IEPduSessionIdentity2 oldPduSessionId, IERequestType requestType, IESNssai sNssa, IEDnn dnn, IEAdditionalInformation additionalInformation) {
        this();
        this.payloadContainerType = payloadContainerType;
        this.payloadContainer = payloadContainer;
        this.pduSessionId = pduSessionId;
        this.oldPduSessionId = oldPduSessionId;
        this.requestType = requestType;
        this.sNssa = sNssa;
        this.dnn = dnn;
        this.additionalInformation = additionalInformation;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE1("payloadContainerType");
        builder.mandatoryIE("payloadContainer");

        builder.optionalIE(0x12, "pduSessionId");
        builder.optionalIE(0x59, "oldPduSessionId");
        builder.optionalIE1(0x8, "requestType");
        builder.optionalIE(0x22, "sNssa");
        builder.optionalIE(0x25, "dnn");
        builder.optionalIE(0x24, "additionalInformation");
    }
}
