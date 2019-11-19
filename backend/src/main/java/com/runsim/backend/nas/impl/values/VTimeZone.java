package com.runsim.backend.nas.impl.values;

import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.Octet;

public class VTimeZone extends NasValue {
    public Octet rawOctet;

    public static VTimeZone decode(OctetInputStream stream) {
        var res = new VTimeZone();
        res.rawOctet = stream.readOctet();
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        stream.writeOctet(rawOctet);
    }
}
