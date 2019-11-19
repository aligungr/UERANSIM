package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.nas.impl.enums.EDaylightSavingTime;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IEDaylightSavingTime extends InformationElement4 {
    public EDaylightSavingTime daylightSavingTime;

    @Override
    protected IEDaylightSavingTime decodeIE4(OctetInputStream stream, int length) {
        var res = new IEDaylightSavingTime();
        res.daylightSavingTime = EDaylightSavingTime.fromValue(stream.readOctetI());
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctet(daylightSavingTime.intValue());
    }
}
