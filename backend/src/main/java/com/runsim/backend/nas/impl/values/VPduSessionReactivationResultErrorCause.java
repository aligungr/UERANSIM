package com.runsim.backend.nas.impl.values;

import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.Octet;

public class VPduSessionReactivationResultErrorCause extends NasValue {

    public Octet pduSessionId;
    public Octet causeValue;

    @Override
    public VPduSessionReactivationResultErrorCause decode(OctetInputStream stream) {
        var res = new VPduSessionReactivationResultErrorCause();
        res.pduSessionId = stream.readOctet();
        res.causeValue = stream.readOctet();
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        stream.writeOctet(pduSessionId);
        stream.writeOctet(causeValue);
    }
}
