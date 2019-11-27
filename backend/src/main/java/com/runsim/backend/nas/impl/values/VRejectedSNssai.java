package com.runsim.backend.nas.impl.values;

import com.runsim.backend.exceptions.EncodingException;
import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.nas.core.ProtocolEnum;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class VRejectedSNssai extends NasValue {
    public ERejectedSNssaiCause cause;
    public VSliceServiceType sst;
    public VSliceDifferentiator sd;

    public static VRejectedSNssai decode(OctetInputStream stream) {
        var res = new VRejectedSNssai();

        int octet = stream.readOctetI();
        res.cause = ERejectedSNssaiCause.fromValue(octet & 0xF);

        int length = octet >> 4 & 0xF;
        if (length >= 1) {
            res.sst = VSliceServiceType.decode(stream);
        }

        if (length >= 2) {
            res.sd = VSliceDifferentiator.decode(stream);
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
