package com.runsim.backend.nas.impl.values;

import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.OctetN;

public class VPartialTrackingAreaIdentityList10 extends VPartialTrackingAreaIdentityList {

    public VTrackingAreaIdentity[] tais;

    public static VPartialTrackingAreaIdentityList10 decode(OctetInputStream stream, int count) {
        var res = new VPartialTrackingAreaIdentityList10();
        res.tais = new VTrackingAreaIdentity[count];
        for (int i = 0; i < count; i++) {
            res.tais[i] = VTrackingAreaIdentity.decode(stream);
        }
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        var flags = new OctetN(0, 1);
        flags = flags.setBitRange(0, 4, tais.length);
        flags = flags.setBitRange(5, 6, 0b10);
        stream.writeOctets(flags);
        for (var tai : tais) {
            tai.encode(stream);
        }
    }
}
