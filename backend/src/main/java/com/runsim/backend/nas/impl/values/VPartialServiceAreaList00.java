package com.runsim.backend.nas.impl.values;

import com.runsim.backend.exceptions.EncodingException;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.Octet;
import com.runsim.backend.utils.octets.Octet3;

import java.util.ArrayList;
import java.util.List;

public class VPartialServiceAreaList00 extends VPartialServiceAreaList {
    public VPlmn plmn;
    public List<Octet3> tacs;

    public static VPartialServiceAreaList00 decode(OctetInputStream stream, int count) {
        var res = new VPartialServiceAreaList00();
        res.plmn = VPlmn.decode(stream);
        res.tacs = new ArrayList<>();
        for (int i = 0; i < count; i++)
            res.tacs.add(stream.readOctet3());
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        if (tacs.size() == 0)
            throw new EncodingException("tacs cannot be empty");

        var flags = new Octet();
        flags = flags.setBitRange(0, 4, tacs.size() - 1);
        flags = flags.setBitRange(5, 6, 0b00);
        flags = flags.setBit(7, allowedType.intValue());
        stream.writeOctet(flags);
        plmn.encode(stream);
        for (var tac : tacs) {
            stream.writeOctet3(tac);
        }
    }
}
