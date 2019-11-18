package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.nas.impl.enums.EUesUsageSetting;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IEUesUsageSetting extends InformationElement4 {

    public EUesUsageSetting uesUsageSetting;

    @Override
    protected IEUesUsageSetting decodeIE4(OctetInputStream stream, int length) {
        var res = new IEUesUsageSetting();
        res.uesUsageSetting = EUesUsageSetting.fromValue(stream.readOctetI());
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctet(uesUsageSetting.intValue());
    }
}
