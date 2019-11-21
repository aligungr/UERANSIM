package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement3;
import com.runsim.backend.nas.impl.enums.EGprsTimerValueUnit;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.bits.Bit5;

public class IEGprsTimer extends InformationElement3 {
    public Bit5 timerValue;
    public EGprsTimerValueUnit timerValueUnit;

    @Override
    protected InformationElement3 decodeIE3(OctetInputStream stream) {
        return null;
    }

    @Override
    public void encodeIE3(OctetOutputStream stream) {

    }
}
