package com.runsim.backend.nas.impl.values;

import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.OctetN;

public class VServiceAreaList10 extends VServiceAreaList {
    public VServiceArea[] tais;

    public static VServiceAreaList10 decode(OctetInputStream stream, int count) {
        var res = new VServiceAreaList10();
        res.tais = new VServiceArea[count];
        for (int i = 0; i < count; i++) {
            res.tais[i] = VServiceArea.decode(stream);
        }
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        var flags = new OctetN(0, 1);
        flags = flags.setBitRange(0, 4, tais.length);
        flags = flags.setBitRange(5, 6, 0b10);
        flags = flags.setBit(7, allowedType.value);
        stream.writeOctets(flags);
        for (var tai : tais) {
            tai.encode(stream);
        }
    }
}
