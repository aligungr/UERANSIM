package com.runsim.backend.nas.impl.values;

import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.Octet2;

public class VPlmnIdAccessTech extends NasValue {
    public VPlmn plmnId;
    public Octet2 accessTechnologyIdentifier;

    public static VPlmnIdAccessTech decode(OctetInputStream stream) {
        var res = new VPlmnIdAccessTech();
        res.plmnId = VPlmn.decode(stream);
        res.accessTechnologyIdentifier = stream.readOctet2();
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        plmnId.encode(stream);
        stream.writeOctet2(accessTechnologyIdentifier);
    }
}
