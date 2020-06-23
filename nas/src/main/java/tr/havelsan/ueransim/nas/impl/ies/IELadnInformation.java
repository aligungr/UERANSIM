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

import tr.havelsan.ueransim.nas.NasDecoder;
import tr.havelsan.ueransim.nas.NasEncoder;
import tr.havelsan.ueransim.nas.core.NasValue;
import tr.havelsan.ueransim.nas.core.ies.InformationElement6;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.Utils;

import java.util.Arrays;

public class IELadnInformation extends InformationElement6 {
    public VLadn[] ladns;

    public IELadnInformation() {
    }

    public IELadnInformation(VLadn[] ladns) {
        this.ladns = ladns;
    }

    @Override
    protected IELadnInformation decodeIE6(OctetInputStream stream, int length) {
        var res = new IELadnInformation();
        res.ladns = Utils.decodeList(stream, new VLadn()::decode, length, VLadn.class);
        return res;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        Arrays.stream(ladns).forEach(ladn -> ladn.encode(stream));
    }

    public static class VLadn extends NasValue {
        public IEDnn dnn;
        public IE5gsTrackingAreaIdentityList trackingAreaIdentityList;

        public VLadn() {
        }

        public VLadn(IEDnn dnn, IE5gsTrackingAreaIdentityList trackingAreaIdentityList) {
            this.dnn = dnn;
            this.trackingAreaIdentityList = trackingAreaIdentityList;
        }

        @Override
        public VLadn decode(OctetInputStream stream) {
            var res = new VLadn();
            res.dnn = NasDecoder.ie2346(stream, IEDnn.class);
            res.trackingAreaIdentityList = NasDecoder.ie2346(stream, IE5gsTrackingAreaIdentityList.class);
            return res;
        }

        @Override
        public void encode(OctetOutputStream stream) {
            NasEncoder.ie2346(stream, dnn);
            NasEncoder.ie2346(stream, trackingAreaIdentityList);
        }
    }
}
