/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.NasValue;
import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.nas.impl.values.VSliceDifferentiator;
import tr.havelsan.ueransim.nas.impl.values.VSliceServiceType;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.exceptions.EncodingException;

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
