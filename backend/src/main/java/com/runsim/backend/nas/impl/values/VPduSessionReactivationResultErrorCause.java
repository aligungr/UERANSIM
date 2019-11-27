package com.runsim.backend.nas.impl.values;

import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.nas.impl.enums.EMmCause;
import com.runsim.backend.nas.impl.enums.EPduSessionIdentity;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class VPduSessionReactivationResultErrorCause extends NasValue {
    public EPduSessionIdentity pduSessionId;
    public EMmCause causeValue;

    public static VPduSessionReactivationResultErrorCause decode(OctetInputStream stream) {
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
