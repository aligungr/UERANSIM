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

import tr.havelsan.ueransim.core.exceptions.DecodingException;
import tr.havelsan.ueransim.nas.core.NasValue;
import tr.havelsan.ueransim.nas.core.ies.InformationElement6;
import tr.havelsan.ueransim.nas.impl.enums.EMmCause;
import tr.havelsan.ueransim.nas.impl.enums.EPduSessionIdentity;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;

public class IEPduSessionReactivationResultErrorCause extends InformationElement6 {
    public VPduSessionReactivationResultErrorCause[] values;

    public IEPduSessionReactivationResultErrorCause() {
    }

    public IEPduSessionReactivationResultErrorCause(VPduSessionReactivationResultErrorCause[] values) {
        this.values = values;
    }

    @Override
    protected IEPduSessionReactivationResultErrorCause decodeIE6(OctetInputStream stream, int length) {
        var res = new IEPduSessionReactivationResultErrorCause();
        if (length % 2 != 0)
            throw new DecodingException("length % 2 != 0");

        var values = new VPduSessionReactivationResultErrorCause[length / 2];
        for (int i = 0; i < values.length; i++) {
            values[i] = new VPduSessionReactivationResultErrorCause().decode(stream);
        }

        res.values = values;
        return res;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        for (var item : values) {
            item.encode(stream);
        }
    }

    public static class VPduSessionReactivationResultErrorCause extends NasValue {
        public EPduSessionIdentity pduSessionId;
        public EMmCause causeValue;

        public VPduSessionReactivationResultErrorCause() {
        }

        public VPduSessionReactivationResultErrorCause(EPduSessionIdentity pduSessionId, EMmCause causeValue) {
            this.pduSessionId = pduSessionId;
            this.causeValue = causeValue;
        }

        @Override
        public VPduSessionReactivationResultErrorCause decode(OctetInputStream stream) {
            var res = new VPduSessionReactivationResultErrorCause();
            res.pduSessionId = EPduSessionIdentity.fromValue(stream.readOctetI());
            res.causeValue = EMmCause.fromValue(stream.readOctetI());
            return res;
        }

        @Override
        public void encode(OctetOutputStream stream) {
            stream.writeOctet(pduSessionId.intValue());
            stream.writeOctet(causeValue.intValue());
        }
    }
}
