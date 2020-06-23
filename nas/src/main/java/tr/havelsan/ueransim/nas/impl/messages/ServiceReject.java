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
import tr.havelsan.ueransim.nas.impl.ies.IE5gMmCause;
import tr.havelsan.ueransim.nas.impl.ies.IEEapMessage;
import tr.havelsan.ueransim.nas.impl.ies.IEGprsTimer2;
import tr.havelsan.ueransim.nas.impl.ies.IEPduSessionStatus;

public class ServiceReject extends PlainMmMessage {
    public IE5gMmCause mmCause;
    public IEPduSessionStatus pduSessionStatus;
    public IEGprsTimer2 t3346Value;
    public IEEapMessage eapMessage;

    public ServiceReject() {
        super(EMessageType.SERVICE_REJECT);
    }

    public ServiceReject(IE5gMmCause mmCause, IEPduSessionStatus pduSessionStatus, IEGprsTimer2 t3346Value, IEEapMessage eapMessage) {
        this();
        this.mmCause = mmCause;
        this.pduSessionStatus = pduSessionStatus;
        this.t3346Value = t3346Value;
        this.eapMessage = eapMessage;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE("mmCause");
        builder.optionalIE(0x50, "pduSessionStatus");
        builder.optionalIE(0x5f, "t3346Value");
        builder.optionalIE(0x78, "eapMessage");
    }
}
