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

import tr.havelsan.ueransim.nas.core.NasValue;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.Octet3;

public class VTrackingAreaIdentity extends NasValue {
    public VPlmn plmn;
    public Octet3 tac;

    public VTrackingAreaIdentity() {
    }

    public VTrackingAreaIdentity(VPlmn plmn, Octet3 tac) {
        this.plmn = plmn;
        this.tac = tac;
    }

    public VTrackingAreaIdentity(VPlmn plmn, int tac) {
        this.plmn = plmn;
        this.tac = new Octet3(tac);
    }

    public VTrackingAreaIdentity(VPlmn plmn, String tac) {
        this.plmn = plmn;
        this.tac = new Octet3(tac);
    }

    public VTrackingAreaIdentity(int mcc, int mnc, int tac) {
        this.plmn = new VPlmn(mcc, mnc);
        this.tac = new Octet3(tac);
    }

    public VTrackingAreaIdentity(int mcc, int mnc, Octet3 tac) {
        this.plmn = new VPlmn(mcc, mnc);
        this.tac = tac;
    }

    public VTrackingAreaIdentity(int mcc, int mnc, String tac) {
        this.plmn = new VPlmn(mcc, mnc);
        this.tac = new Octet3(tac);
    }

    @Override
    public VTrackingAreaIdentity decode(OctetInputStream stream) {
        var res = new VTrackingAreaIdentity();
        res.plmn = new VPlmn().decode(stream);
        res.tac = stream.readOctet3();
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        plmn.encode(stream);
        stream.writeOctet3(tac);
    }
}
