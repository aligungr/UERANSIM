package com.runsim.backend.nas.impl.values;

import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.Octet3;

public class VTrackingAreaIdentity extends NasValue {
    public VPlmn mccMnc;
    public Octet3 tac;

    @Override
    public VTrackingAreaIdentity decode(OctetInputStream stream) {
        var res = new VTrackingAreaIdentity();
        res.mccMnc = new VPlmn().decode(stream);
        res.tac = stream.readOctet3();
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        mccMnc.encode(stream);
        stream.writeOctet3(tac);
    }
}
