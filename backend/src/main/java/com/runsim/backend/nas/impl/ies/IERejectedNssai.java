package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.exceptions.DecodingException;
import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.nas.impl.values.VEmergencyNumberInformation;
import com.runsim.backend.nas.impl.values.VRejectedSNssai;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

import java.util.ArrayList;
import java.util.List;


public class IERejectedNssai extends InformationElement4 {
    public List<VRejectedSNssai> rejectedSNssaiList;

    @Override
    protected IERejectedNssai decodeIE4(OctetInputStream stream, int length) {
        var res = new IERejectedNssai();
        res.rejectedSNssaiList = new ArrayList<>();

        int startIndex = stream.currentIndex();
        while (stream.currentIndex() - startIndex < length) {
            res.rejectedSNssaiList.add(VRejectedSNssai.decode(stream));
        }

        if (stream.currentIndex() - startIndex > length) {
            throw new DecodingException("ie length exceeds the given length");
        }

        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        for (var rejectedSNssai : rejectedSNssaiList) {
            rejectedSNssai.encode(stream);
        }
    }
}
