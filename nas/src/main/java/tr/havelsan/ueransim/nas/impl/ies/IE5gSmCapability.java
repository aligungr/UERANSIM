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

public class IE5gSmCapability extends InformationElement4 {
    public EReflectiveQoS rqos;
    public EMultiHomedIPv6PduSession mh6pdu;

    public IE5gSmCapability() {
    }

    public IE5gSmCapability(EReflectiveQoS rqos, EMultiHomedIPv6PduSession mh6pdu) {
        this.rqos = rqos;
        this.mh6pdu = mh6pdu;
    }

    @Override
    protected IE5gSmCapability decodeIE4(OctetInputStream stream, int length) {
        var res = new IE5gSmCapability();
        res.rqos = EReflectiveQoS.fromValue(stream.peekOctetI() & 0b1);
        res.mh6pdu = EMultiHomedIPv6PduSession.fromValue(stream.readOctetI() >> 1 & 0b1);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctet(mh6pdu.intValue() << 1 | rqos.intValue());
    }

    public static class EMultiHomedIPv6PduSession extends ProtocolEnum {
        public static final EMultiHomedIPv6PduSession NOT_SUPPORTED
                = new EMultiHomedIPv6PduSession(0b0, "Multi-homed IPv6 PDU session not supported");
        public static final EMultiHomedIPv6PduSession SUPPORTED
                = new EMultiHomedIPv6PduSession(0b1, "Multi-homed IPv6 PDU session supported");

        private EMultiHomedIPv6PduSession(int value, String name) {
            super(value, name);
        }

        public static EMultiHomedIPv6PduSession fromValue(int value) {
            return fromValueGeneric(EMultiHomedIPv6PduSession.class, value, null);
        }
    }

    public static class EReflectiveQoS extends ProtocolEnum {
        public static final EReflectiveQoS NOT_SUPPORTED
                = new EReflectiveQoS(0b0, "Reflective QoS not supported");
        public static final EReflectiveQoS SUPPORTED
                = new EReflectiveQoS(0b1, "Reflective QoS supported");

        private EReflectiveQoS(int value, String name) {
            super(value, name);
        }

        public static EReflectiveQoS fromValue(int value) {
            return fromValueGeneric(EReflectiveQoS.class, value, null);
        }
    }
}
