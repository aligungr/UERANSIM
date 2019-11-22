package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement3;
import com.runsim.backend.nas.impl.enums.EGprsTimerValueUnit;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.bits.Bit5;
import com.runsim.backend.utils.octets.Octet;

public class IEGprsTimer extends InformationElement3 {
    public Bit5 timerValue;
    public EGprsTimerValueUnit timerValueUnit;

    @Override
    protected IEGprsTimer decodeIE3(OctetInputStream stream) {
        var res = new IEGprsTimer();
        var octet = stream.readOctet();
        res.timerValue = new Bit5(octet.getBitRangeI(0, 4));
        res.timerValueUnit = EGprsTimerValueUnit.fromValue(octet.getBitRangeI(5, 7));
        return res;
    }

    @Override
    public void encodeIE3(OctetOutputStream stream) {
        var octet = new Octet();
        octet = octet.setBitRange(0, 4, timerValue.intValue());
        octet = octet.setBitRange(5, 5, timerValueUnit.intValue());
        stream.writeOctet(octet);
    }
}
