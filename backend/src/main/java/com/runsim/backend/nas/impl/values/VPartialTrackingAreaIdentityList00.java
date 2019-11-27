package com.runsim.backend.nas.impl.values;

import com.runsim.backend.exceptions.EncodingException;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.Octet;
import com.runsim.backend.utils.octets.Octet3;

public class VPartialTrackingAreaIdentityList00 extends VPartialTrackingAreaIdentityList {
    public VPlmn mccMnc;
    public Octet3[] tacs;

    public static VPartialTrackingAreaIdentityList00 decode(OctetInputStream stream, int count) {
        var res = new VPartialTrackingAreaIdentityList00();
        res.mccMnc = VPlmn.decode(stream);
        res.tacs = new Octet3[count];
        for (int i = 0; i < count; i++) {
            res.tacs[i] = stream.readOctet3();
        }
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        if (tacs.length == 0)
            throw new EncodingException("tacs cannot be empty");

        var flags = new Octet();
        flags = flags.setBitRange(0, 4, tacs.length - 1);
        flags = flags.setBitRange(5, 6, 0b00);
        stream.writeOctet(flags);
        mccMnc.encode(stream);
        for (var tac : tacs) {
            stream.writeOctet3(tac);
        }
    }
}
