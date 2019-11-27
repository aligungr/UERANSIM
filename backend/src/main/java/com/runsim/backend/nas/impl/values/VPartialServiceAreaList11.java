package com.runsim.backend.nas.impl.values;

import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.Octet;

public class VPartialServiceAreaList11 extends VPartialServiceAreaList {
    public VPlmn plmn;

    public static VPartialServiceAreaList11 decode(OctetInputStream stream, int count) {
        var res = new VPartialServiceAreaList11();
        res.plmn = VPlmn.decode(stream);
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        var flags = new Octet();
        flags = flags.setBitRange(0, 4, 0);
        flags = flags.setBitRange(5, 6, 0b01);
        flags = flags.setBit(7, allowedType.intValue());
        stream.writeOctet(flags);
        plmn.encode(stream);
    }
}
