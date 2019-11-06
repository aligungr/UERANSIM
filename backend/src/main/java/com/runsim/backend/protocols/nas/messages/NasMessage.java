package com.runsim.backend.protocols.nas.messages;

import com.runsim.backend.protocols.core.OctetInputStream;
import com.runsim.backend.protocols.core.ProtocolValue;

public abstract class NasMessage extends ProtocolValue {
    public abstract NasMessage decodeMessage(OctetInputStream stream);
}
