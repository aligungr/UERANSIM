package com.runsim.backend.nas.impl.values;

import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.Octet;

public class VPartialServiceAreaList10 extends VPartialServiceAreaList {
    public VServiceArea[] tais;

    public static VPartialServiceAreaList10 decode(OctetInputStream stream, int count) {
        var res = new VPartialServiceAreaList10();
        res.tais = new VServiceArea[count];
        for (int i = 0; i < count; i++) {
            res.tais[i] = VServiceArea.decode(stream);
        }
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        var flags = new Octet();
        flags = flags.setBitRange(0, 4, tais.length);
        flags = flags.setBitRange(5, 6, 0b10);
        flags = flags.setBit(7, allowedType.intValue());
        stream.writeOctet(flags);
        for (var tai : tais) {
            tai.encode(stream);
        }
    }
}
