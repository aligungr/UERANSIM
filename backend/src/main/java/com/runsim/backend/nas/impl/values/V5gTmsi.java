package com.runsim.backend.nas.impl.values;

import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.Octet;
import com.runsim.backend.utils.octets.Octet4;

public class V5gTmsi extends NasValue {

    public Octet4 value;

    @Override
    public NasValue decode(OctetInputStream stream) {
        Octet octet3 = stream.readOctet();
        Octet octet2 = stream.readOctet();
        Octet octet1 = stream.readOctet();
        Octet octet0 = stream.readOctet();

        var res = new V5gTmsi();
        res.value = new Octet4(octet3, octet2, octet1, octet0);
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        throw new NotImplementedException("");
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
