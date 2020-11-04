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

package tr.havelsan.ueransim.app.common.configs;

import tr.havelsan.ueransim.app.common.Supi;
import tr.havelsan.ueransim.app.ue.mm.MmKeyManagement;
import tr.havelsan.ueransim.nas.impl.ies.IEDnn;
import tr.havelsan.ueransim.nas.impl.ies.IESNssai;
import tr.havelsan.ueransim.nas.impl.values.VPlmn;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class UeConfig {
    public final OctetString key;
    public final OctetString op;
    public final OctetString amf;
    public final String imei;
    public final Supi supi;
    public final VPlmn plmn;

    public final boolean smsOverNasSupported;
    public final IESNssai[] requestedNssai;
    public final IEDnn dnn;

    public final String snn; // Auto constructed

    public UeConfig(OctetString key, OctetString op, OctetString amf, String imei, Supi supi, VPlmn plmn,
                    boolean smsOverNasSupported, IESNssai[] requestedNssai, IEDnn dnn) {
        this.key = key;
        this.op = op;
        this.amf = amf;
        this.imei = imei;
        this.supi = supi;
        this.plmn = plmn;
        this.smsOverNasSupported = smsOverNasSupported;
        this.requestedNssai = requestedNssai;
        this.dnn = dnn;

        this.snn = MmKeyManagement.constructServingNetworkName(plmn);
    }

    public UeConfig(String key, String op, String amf, String imei, String supi, VPlmn plmn,
                    boolean smsOverNasSupported, IESNssai[] requestedNssai, String dnn) {
        this(new OctetString(key), new OctetString(op), new OctetString(amf), imei,
                Supi.parse(supi), plmn, smsOverNasSupported, requestedNssai, new IEDnn(dnn));
    }
}
