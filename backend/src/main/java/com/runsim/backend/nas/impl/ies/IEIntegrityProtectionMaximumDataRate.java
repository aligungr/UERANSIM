package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement3;
import com.runsim.backend.nas.impl.enums.EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink;
import com.runsim.backend.nas.impl.enums.EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IEIntegrityProtectionMaximumDataRate extends InformationElement3 {
    // couldn't have been a longer name I guess
    public EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink maxRateUplink;
    public EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink maxRateDownlink;

    @Override
    protected IEIntegrityProtectionMaximumDataRate decodeIE3(OctetInputStream stream) {
        var res = new IEIntegrityProtectionMaximumDataRate();
        res.maxRateUplink = EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink.fromValue(stream.readOctetI());
        res.maxRateDownlink = EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink.fromValue(stream.readOctetI());
        return res;
    }

    @Override
    public void encodeIE3(OctetOutputStream stream) {
        stream.writeOctet(maxRateUplink.intValue());
        stream.writeOctet(maxRateDownlink.intValue());
    }
}
