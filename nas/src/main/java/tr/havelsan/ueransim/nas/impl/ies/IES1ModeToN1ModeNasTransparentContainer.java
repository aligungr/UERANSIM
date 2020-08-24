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
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfCipheringAlgorithm;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfIntegrityProtectionAlgorithm;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfSecurityContext;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.bits.Bit3;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.Octet2;
import tr.havelsan.ueransim.utils.octets.Octet4;

public class IES1ModeToN1ModeNasTransparentContainer extends InformationElement4 {
    public Octet4 mac;
    public ETypeOfCipheringAlgorithm cipheringAlg;
    public ETypeOfIntegrityProtectionAlgorithm integrityProtectionAlg;
    public Bit3 keySetIdentifierIn5g;
    public ETypeOfSecurityContext tsc;
    public Bit3 ncc;
    public Octet2 ueSecurityCapability5g;
    public Octet2 ueSecurityCapabilityEps;

    @Override
    protected IES1ModeToN1ModeNasTransparentContainer decodeIE4(OctetInputStream stream, int length) {
        var res = new IES1ModeToN1ModeNasTransparentContainer();
        res.mac = stream.readOctet4();

        var octet = stream.readOctet();
        res.integrityProtectionAlg = ETypeOfIntegrityProtectionAlgorithm.fromValue(octet.getBitRangeI(0, 3));
        res.cipheringAlg = ETypeOfCipheringAlgorithm.fromValue(octet.getBitRangeI(4, 7));

        octet = stream.readOctet();
        res.keySetIdentifierIn5g = new Bit3(octet.getBitRangeI(0, 2));
        res.tsc = ETypeOfSecurityContext.fromValue(octet.getBitI(3));
        res.ncc = new Bit3(octet.getBitRangeI(4, 6));

        res.ueSecurityCapability5g = stream.readOctet2();

        if (length >= 9) {
            ueSecurityCapabilityEps = stream.readOctet2();
        }

        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctet4(mac);
        stream.writeOctet((cipheringAlg.intValue() << 4) | integrityProtectionAlg.intValue());
        stream.writeOctet(new Octet().setBitRange(0, 2, keySetIdentifierIn5g.intValue())
                .setBit(3, tsc.intValue()).setBitRange(4, 6, ncc.intValue()));
        stream.writeOctet2(ueSecurityCapability5g);
        if (ueSecurityCapabilityEps != null) {
            stream.writeOctet2(ueSecurityCapabilityEps);
        }
    }
}
