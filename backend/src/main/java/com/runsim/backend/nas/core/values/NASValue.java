package com.runsim.backend.nas.core.values;

import com.runsim.backend.nas.core.BitInputStream;
import com.runsim.backend.nas.core.BitOutputStream;

public abstract class NASValue {

    public abstract void encode(BitOutputStream stream);

    public abstract void decode(BitInputStream stream);

    public abstract String display();
}
