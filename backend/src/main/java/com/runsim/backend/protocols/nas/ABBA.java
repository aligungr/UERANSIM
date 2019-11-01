package com.runsim.backend.protocols.nas;

import com.runsim.backend.protocols.bits.Bit8;
import com.runsim.backend.protocols.core.ProtocolValue;

public class ABBA extends ProtocolValue {
    // Currently only defined value is 0x0000 with length 2 (bytes)
    // (3GPP TS 33.501, 15.2.0)

    public Bit8 length;
    public Bit8[] contents;
}
