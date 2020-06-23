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

public class IE5gMmCapability extends InformationElement4 {
    public EEpcNasSupported s1Mode;
    public EHandoverAttachSupported hoAttach;
    public ELtePositioningProtocolCapability lpp;

    public IE5gMmCapability() {
    }

    public IE5gMmCapability(EEpcNasSupported s1Mode, EHandoverAttachSupported hoAttach, ELtePositioningProtocolCapability lpp) {
        this.s1Mode = s1Mode;
        this.hoAttach = hoAttach;
        this.lpp = lpp;
    }

    @Override
    protected IE5gMmCapability decodeIE4(OctetInputStream stream, int length) {
        var res = new IE5gMmCapability();
        var octet = stream.readOctet();
        s1Mode = EEpcNasSupported.fromValue(octet.getBitI(0));
        hoAttach = EHandoverAttachSupported.fromValue(octet.getBitI(1));
        lpp = ELtePositioningProtocolCapability.fromValue(octet.getBitI(2));
        // other octets are spare (if any)
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        int octet = 0;
        octet |= lpp.intValue();
        octet <<= 1;
        octet |= hoAttach.intValue();
        octet <<= 1;
        octet |= s1Mode.intValue();
        stream.writeOctet(octet);
    }

    public static class EEpcNasSupported extends ProtocolEnum {
        public static final EEpcNasSupported NOT_SUPPORTED
                = new EEpcNasSupported(0b0, "S1 mode not supported");
        public static final EEpcNasSupported SUPPORTED
                = new EEpcNasSupported(0b1, "S1 mode supported");

        private EEpcNasSupported(int value, String name) {
            super(value, name);
        }

        public static EEpcNasSupported fromValue(int value) {
            return fromValueGeneric(EEpcNasSupported.class, value, null);
        }
    }

    public static class ELtePositioningProtocolCapability extends ProtocolEnum {
        public static final ELtePositioningProtocolCapability NOT_SUPPORTED
                = new ELtePositioningProtocolCapability(0b0, "LPP in N1 mode not supported");
        public static final ELtePositioningProtocolCapability SUPPORTED
                = new ELtePositioningProtocolCapability(0b1, "LPP in N1 mode supported");

        private ELtePositioningProtocolCapability(int value, String name) {
            super(value, name);
        }

        public static ELtePositioningProtocolCapability fromValue(int value) {
            return fromValueGeneric(ELtePositioningProtocolCapability.class, value, null);
        }
    }

    public static class EHandoverAttachSupported extends ProtocolEnum {
        public static final EHandoverAttachSupported NOT_SUPPORTED
                = new EHandoverAttachSupported(0b0, "not supported");
        public static final EHandoverAttachSupported SUPPORTED
                = new EHandoverAttachSupported(0b1, "supported");

        private EHandoverAttachSupported(int value, String name) {
            super(value, name);
        }

        public static EHandoverAttachSupported fromValue(int value) {
            return fromValueGeneric(EHandoverAttachSupported.class, value, null);
        }
    }
}
