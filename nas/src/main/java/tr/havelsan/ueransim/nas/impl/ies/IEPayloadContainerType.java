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
import tr.havelsan.ueransim.nas.core.ies.InformationElement1;
import tr.havelsan.ueransim.utils.bits.Bit4;

public class IEPayloadContainerType extends InformationElement1 {
    public EPayloadContainerType payloadContainerType;

    public IEPayloadContainerType() {
    }

    public IEPayloadContainerType(EPayloadContainerType payloadContainerType) {
        this.payloadContainerType = payloadContainerType;
    }

    @Override
    public IEPayloadContainerType decodeIE1(Bit4 value) {
        var res = new IEPayloadContainerType();
        res.payloadContainerType = EPayloadContainerType.fromValue(value.intValue() & 0b1111);
        return res;
    }

    @Override
    public int encodeIE1() {
        return payloadContainerType.intValue();
    }

    public static class EPayloadContainerType extends ProtocolEnum {
        public static final EPayloadContainerType N1_SM_INFORMATION
                = new EPayloadContainerType(0b0001, "N1 SM information");
        public static final EPayloadContainerType SMS
                = new EPayloadContainerType(0b0010, "SMS");
        public static final EPayloadContainerType LPP_MESSAGE
                = new EPayloadContainerType(0b0011, "LTE Positioning Protocol (LPP) message container");
        public static final EPayloadContainerType SOR_TRANSPARENT_CONTAINER
                = new EPayloadContainerType(0b0100, "SOR transparent container");
        public static final EPayloadContainerType UE_POLICY_CONTAINER
                = new EPayloadContainerType(0b0101, "UE policy container");
        public static final EPayloadContainerType UE_PARAMETERS_UPDATE_TRANSPARENT_CONTAINER
                = new EPayloadContainerType(0b0110, "UE parameters update transparent container");
        public static final EPayloadContainerType MULTIPLE_PAYLOADS
                = new EPayloadContainerType(0b1111, "Multiple payloads");

        private EPayloadContainerType(int value, String name) {
            super(value, name);
        }

        public static EPayloadContainerType fromValue(int value) {
            return fromValueGeneric(EPayloadContainerType.class, value, null);
        }
    }
}
