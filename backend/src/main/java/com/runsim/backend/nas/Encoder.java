package com.runsim.backend.nas;

import com.runsim.backend.nas.core.messages.NasMessage;

public class Encoder {

    public static byte[] nasPdu(NasMessage pdu) {
        return new NasEncoder().encodeNAS(pdu);
    }
}
