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

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;

public class IEAdditional5gSecurityInformation extends InformationElement4 {
    public EHorizontalDerivationParameter hdp;
    public ERetransmissionOfInitialNasMessageRequest rinmr;

    public IEAdditional5gSecurityInformation() {
    }

    public IEAdditional5gSecurityInformation(EHorizontalDerivationParameter hdp, ERetransmissionOfInitialNasMessageRequest rinmr) {
        this.hdp = hdp;
        this.rinmr = rinmr;
    }

    @Override
    protected IEAdditional5gSecurityInformation decodeIE4(OctetInputStream stream, int length) {
        var res = new IEAdditional5gSecurityInformation();
        res.hdp = EHorizontalDerivationParameter.fromValue(stream.peekOctetI() & 0b1);
        res.rinmr = ERetransmissionOfInitialNasMessageRequest.fromValue(stream.readOctetI() >> 1 & 0b1);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctet(rinmr.intValue() << 1 | hdp.intValue());
    }

    public static class ERetransmissionOfInitialNasMessageRequest extends ProtocolEnum {
        public static final ERetransmissionOfInitialNasMessageRequest NOT_REQUESTED
                = new ERetransmissionOfInitialNasMessageRequest(0b0, "Retransmission of the initial NAS message not requested");
        public static final ERetransmissionOfInitialNasMessageRequest REQUESTED
                = new ERetransmissionOfInitialNasMessageRequest(0b1, "Retransmission of the initial NAS message requested");

        private ERetransmissionOfInitialNasMessageRequest(int value, String name) {
            super(value, name);
        }

        public static ERetransmissionOfInitialNasMessageRequest fromValue(int value) {
            return fromValueGeneric(ERetransmissionOfInitialNasMessageRequest.class, value, null);
        }
    }

    public static class EHorizontalDerivationParameter extends ProtocolEnum {
        public static final EHorizontalDerivationParameter NOT_REQUIRED
                = new EHorizontalDerivationParameter(0b0, "K_AMF derivation is not required");
        public static final EHorizontalDerivationParameter REQUIRED
                = new EHorizontalDerivationParameter(0b1, "K_AMF derivation is required");

        private EHorizontalDerivationParameter(int value, String name) {
            super(value, name);
        }

        public static EHorizontalDerivationParameter fromValue(int value) {
            return fromValueGeneric(EHorizontalDerivationParameter.class, value, null);
        }
    }
}
