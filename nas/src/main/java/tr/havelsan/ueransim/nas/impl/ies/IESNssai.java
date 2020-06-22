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

import tr.havelsan.ueransim.core.exceptions.EncodingException;
import tr.havelsan.ueransim.core.exceptions.ReservedOrInvalidValueException;
import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.nas.impl.values.VSliceDifferentiator;
import tr.havelsan.ueransim.nas.impl.values.VSliceServiceType;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;

public class IESNssai extends InformationElement4 {
    public VSliceServiceType sst;
    public VSliceDifferentiator sd;
    public VSliceServiceType mappedHplmnSst;
    public VSliceDifferentiator mappedHplmnSd;

    public IESNssai() {
    }

    public IESNssai(VSliceServiceType sst, VSliceDifferentiator sd, VSliceServiceType mappedHplmnSst, VSliceDifferentiator mappedHplmnSd) {
        this.sst = sst;
        this.sd = sd;
        this.mappedHplmnSst = mappedHplmnSst;
        this.mappedHplmnSd = mappedHplmnSd;
    }

    @Override
    protected InformationElement4 decodeIE4(OctetInputStream stream, int length) {
        var res = new IESNssai();

        switch (length) {
            case 0b00000001: // SST
                res.sst = new VSliceServiceType().decode(stream);
                break;
            case 0b00000010: // SST and mapped HPLMN SST
                res.sst = new VSliceServiceType().decode(stream);
                res.mappedHplmnSst = new VSliceServiceType().decode(stream);
                break;
            case 0b00000100: // SST and SD
                res.sst = new VSliceServiceType().decode(stream);
                res.sd = new VSliceDifferentiator().decode(stream);
                break;
            case 0b00000101: // SST, SD and mapped HPLMN SST
                res.sst = new VSliceServiceType().decode(stream);
                res.sd = new VSliceDifferentiator().decode(stream);
                res.mappedHplmnSst = new VSliceServiceType().decode(stream);
                break;
            case 0b00001000: // SST, SD, mapped HPLMN SST and mapped HPLMN SD
                res.sst = new VSliceServiceType().decode(stream);
                res.sd = new VSliceDifferentiator().decode(stream);
                res.mappedHplmnSst = new VSliceServiceType().decode(stream);
                res.mappedHplmnSd = new VSliceDifferentiator().decode(stream);
                break;
            default: // All other values are reserved
                throw new ReservedOrInvalidValueException("S-NSSAI Information Element Length Indicator");
        }

        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        // WARNING: Order is important.

        if (sst == null) {
            throw new EncodingException("sst cannot be null");
        }
        sst.encode(stream);

        if (sd != null) sd.encode(stream);
        if (mappedHplmnSst != null) mappedHplmnSst.encode(stream);
        if (mappedHplmnSd != null) mappedHplmnSd.encode(stream);
    }
}
