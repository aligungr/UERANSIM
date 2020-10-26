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

package tr.havelsan.ueransim.app.common;

import tr.havelsan.ueransim.nas.impl.enums.EMccValue;
import tr.havelsan.ueransim.nas.impl.enums.EMncValue;
import tr.havelsan.ueransim.utils.bits.Bit10;
import tr.havelsan.ueransim.utils.bits.Bit6;
import tr.havelsan.ueransim.utils.octets.Octet;

public class Guami {
    public final EMccValue mcc;
    public final EMncValue mnc;
    public final Octet amfRegionId;
    public final Bit10 amfSetId;
    public final Bit6 amfPointer;

    public Guami(EMccValue mcc, EMncValue mnc, Octet amfRegionId, Bit10 amfSetId, Bit6 amfPointer) {
        this.mcc = mcc;
        this.mnc = mnc;
        this.amfRegionId = amfRegionId;
        this.amfSetId = amfSetId;
        this.amfPointer = amfPointer;
    }

    @Override
    public String toString() {
        return "Guami{" +
                "mcc=" + mcc +
                ", mnc=" + mnc +
                ", amfRegionId=" + amfRegionId +
                ", amfSetId=" + amfSetId +
                ", amfPointer=" + amfPointer +
                '}';
    }
}
