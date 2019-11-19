package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement3;
import com.runsim.backend.nas.impl.values.VTimeZone;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IETimeZone extends InformationElement3 {
    public VTimeZone timeZone;

    @Override
    protected IETimeZone decodeIE3(OctetInputStream stream) {
        var res = new IETimeZone();
        res.timeZone = VTimeZone.decode(stream);
        return res;
    }

    @Override
    public void encodeIE3(OctetOutputStream stream) {
        timeZone.encode(stream);
    }
}
