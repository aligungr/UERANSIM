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

public class IEUeStatus extends InformationElement4 {
    public EEmmRegistrationStatus s1ModeReg;
    public E5gMmRegistrationStatus n1ModeReg;

    public IEUeStatus() {
    }

    public IEUeStatus(EEmmRegistrationStatus s1ModeReg, E5gMmRegistrationStatus n1ModeReg) {
        this.s1ModeReg = s1ModeReg;
        this.n1ModeReg = n1ModeReg;
    }

    @Override
    protected IEUeStatus decodeIE4(OctetInputStream stream, int length) {
        int octet = stream.readOctetI();

        var res = new IEUeStatus();
        res.s1ModeReg = EEmmRegistrationStatus.fromValue(octet & 0b1);
        res.n1ModeReg = E5gMmRegistrationStatus.fromValue(octet >> 1 & 0b1);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        int octet = s1ModeReg.intValue() | (n1ModeReg.intValue() << 1);
        stream.writeOctet(octet);
    }

    public static class E5gMmRegistrationStatus extends ProtocolEnum {
        public static final E5gMmRegistrationStatus NOT_REGISTERED
                = new E5gMmRegistrationStatus(0b0, "UE is not in 5GMM-REGISTERED state");
        public static final E5gMmRegistrationStatus REGISTERED
                = new E5gMmRegistrationStatus(0b1, "UE is in 5GMM-REGISTERED state");

        private E5gMmRegistrationStatus(int value, String name) {
            super(value, name);
        }

        public static E5gMmRegistrationStatus fromValue(int value) {
            return fromValueGeneric(E5gMmRegistrationStatus.class, value, null);
        }
    }

    public static class EEmmRegistrationStatus extends ProtocolEnum {
        public static final EEmmRegistrationStatus NOT_REGISTERED
                = new EEmmRegistrationStatus(0b0, "UE is not in EMM-REGISTERED state");
        public static final EEmmRegistrationStatus REGISTERED
                = new EEmmRegistrationStatus(0b1, "UE is in EMM-REGISTERED state");

        private EEmmRegistrationStatus(int value, String name) {
            super(value, name);
        }

        public static EEmmRegistrationStatus fromValue(int value) {
            return fromValueGeneric(EEmmRegistrationStatus.class, value, null);
        }
    }
}
