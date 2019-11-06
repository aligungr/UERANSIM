package com.runsim.backend.protocols.nas.messages;

import com.runsim.backend.protocols.core.OctetInputStream;

public abstract class NasValue {
    public abstract NasValue decode(OctetInputStream stream);
}
