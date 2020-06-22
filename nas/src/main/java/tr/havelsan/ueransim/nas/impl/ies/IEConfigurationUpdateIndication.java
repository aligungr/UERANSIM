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

public class IEConfigurationUpdateIndication extends InformationElement1 {
    public EAcknowledgement ack;
    public ERegistrationRequested red;

    public IEConfigurationUpdateIndication() {
    }

    public IEConfigurationUpdateIndication(EAcknowledgement ack, ERegistrationRequested red) {
        this.ack = ack;
        this.red = red;
    }

    @Override
    public IEConfigurationUpdateIndication decodeIE1(Bit4 value) {
        var res = new IEConfigurationUpdateIndication();
        res.ack = EAcknowledgement.fromValue(value.getBitI(0));
        res.red = ERegistrationRequested.fromValue(value.getBitI(1));
        return res;
    }

    @Override
    public int encodeIE1() {
        return red.intValue() << 1 | ack.intValue();
    }

    public static class EAcknowledgement extends ProtocolEnum {
        public static final EAcknowledgement NOT_REQUESTED
                = new EAcknowledgement(0b0, "acknowledgement not requested");
        public static final EAcknowledgement REQUESTED
                = new EAcknowledgement(0b1, "acknowledgement requested");

        private EAcknowledgement(int value, String name) {
            super(value, name);
        }

        public static EAcknowledgement fromValue(int value) {
            return fromValueGeneric(EAcknowledgement.class, value, null);
        }
    }

    public static class ERegistrationRequested extends ProtocolEnum {
        public static final ERegistrationRequested NOT_REQUESTED
                = new ERegistrationRequested(0b0, "registration not requested");
        public static final ERegistrationRequested REQUESTED
                = new ERegistrationRequested(0b1, "registration requested");

        private ERegistrationRequested(int value, String name) {
            super(value, name);
        }

        public static ERegistrationRequested fromValue(int value) {
            return fromValueGeneric(ERegistrationRequested.class, value, null);
        }
    }
}
