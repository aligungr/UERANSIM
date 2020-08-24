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

package tr.havelsan.ueransim.app.structs;

import tr.havelsan.ueransim.nas.impl.ies.IEDnn;
import tr.havelsan.ueransim.nas.impl.ies.IESNssai;
import tr.havelsan.ueransim.ngap1.UserLocationInformationNr;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class UeConfig {
    public final String snn;
    public final OctetString key;
    public final OctetString op;
    public final OctetString amf;
    public final String imei;
    public final Supi supi;

    public final boolean smsOverNasSupported;
    public final IESNssai[] requestedNssai;
    public final UserLocationInformationNr userLocationInformationNr;
    public final IEDnn dnn;

    public UeConfig(String snn, String key, String op, String amf, String imei, String supi,
                    boolean smsOverNasSupported, IESNssai[] requestedNssai,
                    UserLocationInformationNr userLocationInformationNr, String dnn) {
        this.snn = snn;
        this.key = new OctetString(key);
        this.op = new OctetString(op);
        this.amf = new OctetString(amf);
        this.imei = imei;
        this.supi = Supi.parse(supi);
        this.smsOverNasSupported = smsOverNasSupported;
        this.requestedNssai = requestedNssai;
        this.userLocationInformationNr = userLocationInformationNr;
        this.dnn = new IEDnn(dnn);
    }
}
