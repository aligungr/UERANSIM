package com.runsim.backend.nas.impl.values;

import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.OctetString;

public class VDnn extends NasValue {
    public OctetString data;

    public static VDnn decode(OctetInputStream stream) {
        int length = stream.readOctetI();
        var res = new VDnn();
        res.data = stream.readOctetString(length);
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        stream.writeOctet(data.length);
        stream.writeOctetString(data);
    }
}
