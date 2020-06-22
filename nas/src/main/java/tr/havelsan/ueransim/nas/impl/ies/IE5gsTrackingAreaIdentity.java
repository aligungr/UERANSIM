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

import tr.havelsan.ueransim.nas.core.ies.InformationElement3;
import tr.havelsan.ueransim.nas.impl.enums.EMccValue;
import tr.havelsan.ueransim.nas.impl.enums.EMncValue;
import tr.havelsan.ueransim.nas.impl.values.VPlmn;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.Octet3;

public class IE5gsTrackingAreaIdentity extends InformationElement3 {
    public EMccValue mcc;
    public EMncValue mnc;
    public Octet3 trackingAreaCode;

    public IE5gsTrackingAreaIdentity() {
    }

    public IE5gsTrackingAreaIdentity(EMccValue mcc, EMncValue mnc, Octet3 trackingAreaCode) {
        this.mcc = mcc;
        this.mnc = mnc;
        this.trackingAreaCode = trackingAreaCode;
    }

    @Override
    protected IE5gsTrackingAreaIdentity decodeIE3(OctetInputStream stream) {
        var res = new IE5gsTrackingAreaIdentity();

        // WARNING: MCC and MNC are allowed to contain invalid characters for IE5gsTrackingAreaIdentity
        // but currently opposite of this is assumed.
        //
        // See 3GPP 24.501 f20, Table 9.11.3.8.1: 5GS tracking area identity information element
        VPlmn mccmnc = new VPlmn().decode(stream);

        res.mcc = mccmnc.mcc;
        res.mnc = mccmnc.mnc;
        res.trackingAreaCode = stream.readOctet3();
        return res;
    }

    @Override
    public void encodeIE3(OctetOutputStream stream) {
        var mccmnc = new VPlmn();
        mccmnc.mcc = mcc;
        mccmnc.mnc = mnc;
        mccmnc.encode(stream);
        stream.writeOctet3(trackingAreaCode);
    }
}

