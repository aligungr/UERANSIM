package com.runsim.backend.protocols.nas;

import com.runsim.backend.protocols.bits.Bit3;
import com.runsim.backend.protocols.core.ProtocolValue;

public class NASKeySetIdentifier extends ProtocolValue {
    /*
     * 'no key is available' for UE to network
     * 'reserved' for network to UE
     */
    public static final Bit3 NOT_AVAILABLE_OR_RESERVED = new Bit3(0b111);

    public TypeOfSecurityContext tsc;
    public Bit3 nasKeySetIdentifier;
}
