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
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.nas.impl.enums.EEpcNasSupported;
import tr.havelsan.ueransim.nas.impl.enums.EHandoverAttachSupported;
import tr.havelsan.ueransim.nas.impl.enums.ELtePositioningProtocolCapability;
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
        res.s1Mode = EEpcNasSupported.fromValue(octet.getBitI(0));
        res.hoAttach = EHandoverAttachSupported.fromValue(octet.getBitI(1));
        res.lpp = ELtePositioningProtocolCapability.fromValue(octet.getBitI(2));

        // other octets are spare (if any)
        stream.readOctetString(length - 1);

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
}
