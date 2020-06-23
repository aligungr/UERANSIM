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

import tr.havelsan.ueransim.nas.impl.enums.EMccValue;
import tr.havelsan.ueransim.nas.impl.enums.EMncValue;
import tr.havelsan.ueransim.nas.impl.values.V5gTmsi;
import tr.havelsan.ueransim.nas.impl.values.VAmfSetId;
import tr.havelsan.ueransim.nas.impl.values.VPlmn;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.bits.Bit6;
import tr.havelsan.ueransim.utils.octets.Octet;

public class IE5gGutiMobileIdentity extends IE5gsMobileIdentity {
    public EMccValue mcc;
    public EMncValue mnc;
    public Octet amfRegionId;
    public VAmfSetId amfSetId;
    public Bit6 amfPointer;
    public V5gTmsi tmsi;

    public IE5gGutiMobileIdentity() {
    }

    public IE5gGutiMobileIdentity(EMccValue mcc, EMncValue mnc, Octet amfRegionId, VAmfSetId amfSetId, Bit6 amfPointer, V5gTmsi tmsi) {
        this.mcc = mcc;
        this.mnc = mnc;
        this.amfRegionId = amfRegionId;
        this.amfSetId = amfSetId;
        this.amfPointer = amfPointer;
        this.tmsi = tmsi;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        stream.writeOctet(0xf2); // Flags for 5G-GUTI

        /* Encode MCC and MNC*/
        var mccmnc = new VPlmn();
        mccmnc.mcc = mcc;
        mccmnc.mnc = mnc;
        mccmnc.encode(stream);

        /* Encode region id */
        stream.writeOctet(amfRegionId);

        /* Encode AMF set id and AMF pointer */
        var octets = amfSetId.toIntArray();
        octets[1] |= amfPointer.intValue();
        stream.writeOctets(octets);

        /* Encode TMSI */
        tmsi.encode(stream);
    }
}
