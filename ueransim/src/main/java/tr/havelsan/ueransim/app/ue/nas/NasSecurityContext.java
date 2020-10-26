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

package tr.havelsan.ueransim.app.ue.nas;

import tr.havelsan.ueransim.app.common.NasCount;
import tr.havelsan.ueransim.app.common.SelectedAlgorithms;
import tr.havelsan.ueransim.app.common.UeKeys;
import tr.havelsan.ueransim.app.common.enums.EConnectionIdentifier;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfCipheringAlgorithm;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfIntegrityProtectionAlgorithm;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfSecurityContext;
import tr.havelsan.ueransim.nas.impl.ies.IENasKeySetIdentifier;
import tr.havelsan.ueransim.utils.bits.Bit3;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.Octet2;

public class NasSecurityContext {
    public final UeSimContext ueCtx;
    public IENasKeySetIdentifier ngKsi;

    public NasCount downlinkCount;
    public NasCount uplinkCount;

    public EConnectionIdentifier connectionIdentifier;

    public UeKeys keys;
    public SelectedAlgorithms selectedAlgorithms;

    public NasSecurityContext(UeSimContext ueCtx, ETypeOfSecurityContext tsc, Bit3 ngKsi) {
        this.ueCtx = ueCtx;
        this.ngKsi = new IENasKeySetIdentifier(tsc, ngKsi);
        this.downlinkCount = new NasCount();
        this.uplinkCount = new NasCount();
        this.connectionIdentifier = EConnectionIdentifier.THREE_3GPP_ACCESS;
        this.selectedAlgorithms = new SelectedAlgorithms(ETypeOfIntegrityProtectionAlgorithm.IA0, ETypeOfCipheringAlgorithm.EA0);
        this.keys = new UeKeys();
    }

    public void updateDownlinkCount(NasCount validatedCount) {
        downlinkCount.overflow = validatedCount.overflow;
        downlinkCount.sqn = validatedCount.sqn;
    }

    public NasCount estimatedDownlinkCount(Octet sequenceNumber) {
        var count = new NasCount();
        count.sqn = downlinkCount.sqn;
        count.overflow = downlinkCount.overflow;

        if (count.sqn.longValue() > sequenceNumber.longValue()) {
            count.overflow = new Octet2((count.overflow.longValue() + 1) & 0xFFFF);
        }
        count.sqn = sequenceNumber;
        return count;
    }

    public void countOnEncrypt() {
        uplinkCount.sqn = new Octet((uplinkCount.sqn.longValue() + 1) & 0xFF);
        if (uplinkCount.sqn.longValue() == 0) {
            uplinkCount.overflow = new Octet2((uplinkCount.overflow.longValue() + 1) & 0xFFFF);
        }
    }

    public NasSecurityContext deepCopy() {
        var ctx = new NasSecurityContext(this.ueCtx, this.ngKsi.tsc, this.ngKsi.nasKeySetIdentifier);
        ctx.downlinkCount = this.downlinkCount.deepCopy();
        ctx.uplinkCount = this.uplinkCount.deepCopy();
        ctx.connectionIdentifier = this.connectionIdentifier;
        ctx.keys = this.keys.deepCopy();
        ctx.selectedAlgorithms = this.selectedAlgorithms;
        return ctx;
    }
}
