package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.nas.impl.values.VRejectedSNssai;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.Utils;

import java.util.List;

public class IERejectedNssai extends InformationElement4 {
    public List<VRejectedSNssai> rejectedSNssaiList;

    @Override
    protected IERejectedNssai decodeIE4(OctetInputStream stream, int length) {
        var res = new IERejectedNssai();
        res.rejectedSNssaiList = Utils.decodeList(stream, VRejectedSNssai::decode, 0, length);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        for (var rejectedSNssai : rejectedSNssaiList) {
            rejectedSNssai.encode(stream);
        }
    }
}
