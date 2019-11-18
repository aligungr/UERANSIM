package com.runsim.backend.nas.impl.values;

import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class VEmergencyNumberInformation extends NasValue {
    public VEmergencyServiceCategory emergencyServiceCategory;
    public String number;

    public static VEmergencyNumberInformation decode(OctetInputStream stream) {
        var res = new VEmergencyNumberInformation();
        int length = stream.readOctetI() - 1;
        res.emergencyServiceCategory = VEmergencyServiceCategory.decode(stream);
        res.number = NasDecoder.bcdString(stream, length, false);
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        emergencyServiceCategory.encode(stream);
        NasEncoder.bcdString(stream, number, -1, false, null);
    }
}
