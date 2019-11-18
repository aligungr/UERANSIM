package com.runsim.backend.nas.impl.values;

import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.Octet3;

public class VServiceArea extends NasValue {
    public VMccMnc mccMnc;
    public Octet3 tac;

    public static VServiceArea decode(OctetInputStream stream) {
        var res = new VServiceArea();
        res.mccMnc = VMccMnc.decode(stream);
        res.tac = stream.readOctet3();
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        mccMnc.encode(stream);
        stream.writeOctet3(tac);
    }
}
