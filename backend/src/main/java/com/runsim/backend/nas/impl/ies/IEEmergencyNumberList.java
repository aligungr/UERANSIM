package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.exceptions.DecodingException;
import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.nas.impl.values.VEmergencyNumberInformation;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

import java.util.ArrayList;
import java.util.List;

public class IEEmergencyNumberList extends InformationElement4 {

    public List<VEmergencyNumberInformation> emergencyNumberInformations;

    @Override
    protected IEEmergencyNumberList decodeIE4(OctetInputStream stream, int length) {
        var res = new IEEmergencyNumberList();
        res.emergencyNumberInformations = new ArrayList<>();

        int startIndex = stream.currentIndex();
        while (stream.currentIndex() - startIndex < length) {
            emergencyNumberInformations.add(VEmergencyNumberInformation.decode(stream));
        }

        if (stream.currentIndex() - startIndex > length) {
            throw new DecodingException("ie length exceeds the given length");
        }

        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        for (var emergencyNumberInformation : emergencyNumberInformations) {
            emergencyNumberInformation.encode(stream);
        }
    }
}
