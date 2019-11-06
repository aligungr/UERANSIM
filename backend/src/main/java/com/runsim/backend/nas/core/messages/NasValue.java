package com.runsim.backend.nas.core.messages;

import com.runsim.backend.utils.OctetInputStream;

public abstract class NasValue {
    public abstract NasValue decode(OctetInputStream stream);
}
