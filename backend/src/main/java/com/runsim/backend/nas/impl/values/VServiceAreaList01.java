package com.runsim.backend.nas.impl.values;

import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.Octet;
import com.runsim.backend.utils.octets.Octet3;

public class VServiceAreaList01 extends VServiceAreaList {
    public VMccMnc mccMnc;
    public Octet3 tac;

    public static VServiceAreaList01 decode(OctetInputStream stream, int count) {
        var res = new VServiceAreaList01();
        res.mccMnc = VMccMnc.decode(stream);
        res.tac = stream.readOctet3();
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        var flags = new Octet(0);
        flags = flags.setBitRange(0, 4, 1);
        flags = flags.setBitRange(5, 6, 0b01);
        flags = flags.setBit(7, allowedType.intValue());
        stream.writeOctet(flags);
        mccMnc.encode(stream);
        stream.writeOctet3(tac);
    }
}
