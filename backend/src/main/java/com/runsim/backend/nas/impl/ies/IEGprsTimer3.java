package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.nas.impl.enums.EGprsTimerValueUnit;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.bits.Bit5;

public class IEGprsTimer3 extends InformationElement4 {

    public Bit5 timerValue;
    public EGprsTimerValueUnit unit;

    @Override
    protected IEGprsTimer3 decodeIE4(OctetInputStream stream, int length) {
        var octet = stream.readOctet();

        var res = new IEGprsTimer3();
        res.timerValue = new Bit5(octet.getBitRangeI(0, 4));
        res.unit = EGprsTimerValueUnit.fromValue(octet.getBitRangeI(5, 7));
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {

    }
}
