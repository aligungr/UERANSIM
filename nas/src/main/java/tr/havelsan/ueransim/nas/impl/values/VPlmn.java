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

package tr.havelsan.ueransim.nas.impl.values;

import tr.havelsan.ueransim.core.Constants;
import tr.havelsan.ueransim.core.exceptions.EncodingException;
import tr.havelsan.ueransim.nas.core.NasValue;
import tr.havelsan.ueransim.nas.impl.enums.EMccValue;
import tr.havelsan.ueransim.nas.impl.enums.EMncValue;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;

public class VPlmn extends NasValue {
    public EMccValue mcc;
    public EMncValue mnc;

    public VPlmn() {
    }

    public VPlmn(EMccValue mcc, EMncValue mnc) {
        this.mcc = mcc;
        this.mnc = mnc;
    }

    public VPlmn(int mcc, int mnc) {
        this.mcc = EMccValue.fromValue(mcc);
        this.mnc = EMncValue.fromValue(mnc);
    }

    @Override
    public VPlmn decode(OctetInputStream stream) {
        var res = new VPlmn();

        /* Decode MCC */
        int octet1 = stream.readOctetI();
        int mcc1 = octet1 & 0b1111;
        int mcc2 = (octet1 >> 4) & 0b1111;
        int octet2 = stream.readOctetI();
        int mcc3 = octet2 & 0b1111;
        int mcc = 100 * mcc1 + 10 * mcc2 + mcc3;
        res.mcc = EMccValue.fromValue(mcc);

        /* Decode MNC */
        int mnc3 = (octet2 >> 4) & 0b1111;
        int octet3 = stream.readOctetI();
        int mnc1 = octet3 & 0b1111;
        int mnc2 = (octet3 >> 4) & 0b1111;
        int mnc = 10 * mnc1 + mnc2;
        if ((mnc3 != 0xf) || (octet1 == 0xff && octet2 == 0xff && octet3 == 0xff)) {
            mnc = 10 * mnc + mnc3;
        }
        res.mnc = EMncValue.fromValue(mnc);
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        int mcc = this.mcc.intValue();
        int mcc3 = mcc % 10;
        int mcc2 = (mcc % 100) / 10;
        int mcc1 = (mcc % 1000) / 100;

        if (this.mnc == null)
            throw new EncodingException("mnc is null");

        int mnc = this.mnc.intValue();
        boolean longMnc = this.mnc.isLongMnc();

        if (Constants.ALWAYS_LONG_MNC)
            longMnc = true;

        int mnc1 = longMnc ? (mnc % 1000) / 100 : (mnc % 100) / 10;
        int mnc2 = longMnc ? (mnc % 100) / 10 : (mnc % 10);
        int mnc3 = longMnc ? (mnc % 10) : 0xF;

        int octet1 = mcc2 << 4 | mcc1;
        int octet2 = mnc3 << 4 | mcc3;
        int octet3 = mnc2 << 4 | mnc1;

        stream.writeOctet(octet1);
        stream.writeOctet(octet2);
        stream.writeOctet(octet3);
    }
}