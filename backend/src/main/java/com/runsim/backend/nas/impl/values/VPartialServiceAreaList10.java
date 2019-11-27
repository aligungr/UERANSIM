package com.runsim.backend.nas.impl.values;

import com.runsim.backend.exceptions.EncodingException;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.Octet;

import java.util.ArrayList;
import java.util.List;

public class VPartialServiceAreaList10 extends VPartialServiceAreaList {
    public List<VTrackingAreaIdentity> tais;

    public static VPartialServiceAreaList10 decode(OctetInputStream stream, int count) {
        var res = new VPartialServiceAreaList10();
        res.tais = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            res.tais.add(VTrackingAreaIdentity.decode(stream));
        }
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        if (tais.size() == 0)
            throw new EncodingException("tais cannot be empty");

        var flags = new Octet();
        flags = flags.setBitRange(0, 4, tais.size() - 1);
        flags = flags.setBitRange(5, 6, 0b10);
        flags = flags.setBit(7, allowedType.intValue());
        stream.writeOctet(flags);
        for (var tai : tais) {
            tai.encode(stream);
        }
    }
}
