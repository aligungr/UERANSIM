package com.runsim.backend.nas.impl.values;

import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.Octet;
import com.runsim.backend.utils.octets.OctetString;

public class VEpsParameter extends NasValue {
    public Octet epsParameterIdentifier;
    public OctetString content;

    @Override
    public void encode(OctetOutputStream stream) {
        stream.writeOctet(epsParameterIdentifier);
        stream.writeOctet(content.length);
        stream.writeOctetString(content);
    }

    public static VEpsParameter decode(OctetInputStream stream) {
        var res = new VEpsParameter();
        res.epsParameterIdentifier = stream.readOctet();
        int length = stream.readOctetI();
        res.content = stream.readOctetString(length);
        return res;
    }
}
