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
import tr.havelsan.ueransim.nas.core.NasValue;
import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.nas.impl.values.VSliceDifferentiator;
import tr.havelsan.ueransim.nas.impl.values.VSliceServiceType;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.Utils;

public class IERejectedNssai extends InformationElement4 {
    public VRejectedSNssai[] rejectedSNssaiList;

    public IERejectedNssai() {
    }

    public IERejectedNssai(VRejectedSNssai[] rejectedSNssaiList) {
        this.rejectedSNssaiList = rejectedSNssaiList;
    }

    @Override
    protected IERejectedNssai decodeIE4(OctetInputStream stream, int length) {
        var res = new IERejectedNssai();
        res.rejectedSNssaiList = Utils.decodeList(stream, new VRejectedSNssai()::decode, length, VRejectedSNssai.class);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        for (var rejectedSNssai : rejectedSNssaiList) {
            rejectedSNssai.encode(stream);
        }
    }

    public static class VRejectedSNssai extends NasValue {
        public ERejectedSNssaiCause cause;
        public VSliceServiceType sst;
        public VSliceDifferentiator sd;

        public VRejectedSNssai() {
        }

        public VRejectedSNssai(ERejectedSNssaiCause cause, VSliceServiceType sst, VSliceDifferentiator sd) {
            this.cause = cause;
            this.sst = sst;
            this.sd = sd;
        }

        @Override
        public VRejectedSNssai decode(OctetInputStream stream) {
            var res = new VRejectedSNssai();

            int octet = stream.readOctetI();
            res.cause = ERejectedSNssaiCause.fromValue(octet & 0xF);

            int length = octet >> 4 & 0xF;
            if (length >= 1) {
                res.sst = new VSliceServiceType().decode(stream);
            }

            if (length >= 2) {
                res.sd = new VSliceDifferentiator().decode(stream);
            }

            return res;
        }

        @Override
        public void encode(OctetOutputStream stream) {
            int totalLength = 0;
            if (sd != null && sst == null) {
                throw new EncodingException("sst must not be null if sd is not null");
            }
            if (sst != null) totalLength++;
            if (sd != null) totalLength += 3;

            int octet = totalLength << 4 | cause.intValue();
            stream.writeOctet(octet);

            if (sst != null) {
                sst.encode(stream);
            }
            if (sd != null) {
                sd.encode(stream);
            }
        }
    }

    public static class ERejectedSNssaiCause extends ProtocolEnum {
        public static final ERejectedSNssaiCause NA_IN_PLMN
                = new ERejectedSNssaiCause(0b0, "S-NSSAI not available in the current PLMN");
        public static final ERejectedSNssaiCause NA_IN_RA
                = new ERejectedSNssaiCause(0b1, "S-NSSAI not available in the current registration area");

        private ERejectedSNssaiCause(int value, String name) {
            super(value, name);
        }

        public static ERejectedSNssaiCause fromValue(int value) {
            return fromValueGeneric(ERejectedSNssaiCause.class, value, null);
        }
    }
}
