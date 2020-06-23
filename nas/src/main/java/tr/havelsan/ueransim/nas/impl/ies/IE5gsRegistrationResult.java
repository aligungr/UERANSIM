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

public class IE5gsRegistrationResult extends InformationElement4 {
    public ESmsOverNasTransportAllowed smsOverNasAllowed;
    public E5gsRegistrationResult registrationResult;

    public IE5gsRegistrationResult() {
    }

    public IE5gsRegistrationResult(ESmsOverNasTransportAllowed smsOverNasAllowed, E5gsRegistrationResult registrationResult) {
        this.smsOverNasAllowed = smsOverNasAllowed;
        this.registrationResult = registrationResult;
    }

    @Override
    protected InformationElement4 decodeIE4(OctetInputStream stream, int length) {
        int value = stream.readOctetI();

        var res = new IE5gsRegistrationResult();
        res.registrationResult = E5gsRegistrationResult.fromValue(value & 0b111);
        res.smsOverNasAllowed = ESmsOverNasTransportAllowed.fromValue(value >> 3 & 0b1);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctet(smsOverNasAllowed.intValue() << 3 | registrationResult.intValue());
    }

    public static class ESmsOverNasTransportAllowed extends ProtocolEnum {
        public static final ESmsOverNasTransportAllowed NOT_ALLOWED
                = new ESmsOverNasTransportAllowed(0b0, "SMS over NAS not allowed");
        public static final ESmsOverNasTransportAllowed ALLOWED
                = new ESmsOverNasTransportAllowed(0b1, "SMS over NAS allowed");

        private ESmsOverNasTransportAllowed(int value, String name) {
            super(value, name);
        }

        public static ESmsOverNasTransportAllowed fromValue(int value) {
            return fromValueGeneric(ESmsOverNasTransportAllowed.class, value, null);
        }
    }

    public static class E5gsRegistrationResult extends ProtocolEnum {
        public static final E5gsRegistrationResult THREEGPP_ACCESS
                = new E5gsRegistrationResult(0b001, "3GPP access");
        public static final E5gsRegistrationResult NON_THREEGPP_ACCESS
                = new E5gsRegistrationResult(0b010, "Non-3GPP access");
        public static final E5gsRegistrationResult THREEGPP_ACCESS_AND_NON_THREEGPP_ACCESS
                = new E5gsRegistrationResult(0b011, "3GPP access and non-3GPP access");

        private E5gsRegistrationResult(int value, String name) {
            super(value, name);
        }

        public static E5gsRegistrationResult fromValue(int value) {
            return fromValueGeneric(E5gsRegistrationResult.class, value, null);
        }
    }
}
