package com.runsim.backend.nas.impl.values;

import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class VRejectedSNssa extends NasValue {

    @Override
    public VRejectedSNssa decode(OctetInputStream stream) {
        throw new NotImplementedException("");
    }

    @Override
    public void encode(OctetOutputStream stream) {
        throw new NotImplementedException("");
    }
}
