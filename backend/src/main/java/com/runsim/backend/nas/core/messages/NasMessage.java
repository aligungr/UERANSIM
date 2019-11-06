package com.runsim.backend.nas.core.messages;

import com.runsim.backend.nas.core.ProtocolValue;
import com.runsim.backend.utils.OctetInputStream;

public abstract class NasMessage extends ProtocolValue {
    public abstract NasMessage decodeMessage(OctetInputStream stream);
}
