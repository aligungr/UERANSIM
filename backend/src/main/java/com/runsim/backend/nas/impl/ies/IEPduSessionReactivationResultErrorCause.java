package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.exceptions.DecodingException;
import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.nas.core.ies.InformationElement6;
import com.runsim.backend.nas.impl.enums.EMmCause;
import com.runsim.backend.nas.impl.enums.EPduSessionIdentity;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

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
