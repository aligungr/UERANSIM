package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.nas.impl.values.VEmergencyNumberInformation;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.Utils;

import java.util.List;

public class IEEmergencyNumberList extends InformationElement4 {
    public List<VEmergencyNumberInformation> emergencyNumberInformations;

    @Override
    protected IEEmergencyNumberList decodeIE4(OctetInputStream stream, int length) {
        var res = new IEEmergencyNumberList();
        res.emergencyNumberInformations = Utils.decodeList(stream, VEmergencyNumberInformation::decode, 0, length);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        emergencyNumberInformations.forEach(item -> item.encode(stream));
    }
}
