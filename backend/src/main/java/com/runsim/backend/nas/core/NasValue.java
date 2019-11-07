package com.runsim.backend.nas.core;

import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public abstract class NasValue {
    public abstract NasValue decode(OctetInputStream stream);

    public abstract void encode(OctetOutputStream stream);
}
