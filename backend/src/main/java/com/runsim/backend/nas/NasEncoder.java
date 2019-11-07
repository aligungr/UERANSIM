package com.runsim.backend.nas;

import com.runsim.backend.nas.core.messages.NasMessage;
import com.runsim.backend.utils.OctetOutputStream;

public class NasEncoder {

    public byte[] encodeNAS(NasMessage nasPdu) {
        var stream = new OctetOutputStream();
        nasPdu.encodeMessage(stream);
        return stream.toByteArray();
    }
}
