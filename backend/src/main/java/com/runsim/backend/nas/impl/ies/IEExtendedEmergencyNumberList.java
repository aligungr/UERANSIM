package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.nas.core.ies.InformationElement6;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IEExtendedEmergencyNumberList extends InformationElement6 {

    @Override
    protected InformationElement6 decodeIE6(OctetInputStream stream, int length) {
        throw new NotImplementedException("");
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        throw new NotImplementedException("");
    }
}
