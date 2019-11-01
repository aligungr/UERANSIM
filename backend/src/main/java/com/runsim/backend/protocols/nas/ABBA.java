package com.runsim.backend.protocols.nas;

import com.runsim.backend.protocols.core.ProtocolValue;
import com.runsim.backend.protocols.octets.Octet;
import com.runsim.backend.protocols.octets.OctetString;

public class ABBA extends ProtocolValue {
    // Currently only defined value is 0x0000 with length 2 (bytes)
    // (3GPP TS 33.501, 15.2.0)

    public Octet length;
    public OctetString contents;
}
