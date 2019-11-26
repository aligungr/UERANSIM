package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement6;
import com.runsim.backend.nas.impl.values.VLadn;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.Utils;

import java.util.List;

public class IELadnInformation extends InformationElement6 {
    public List<VLadn> ladns;

    @Override
    protected IELadnInformation decodeIE6(OctetInputStream stream, int length) {
        var res = new IELadnInformation();
        res.ladns = Utils.decodeList(stream, VLadn::decode, 0, length);
        return res;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        ladns.forEach(ladn -> ladn.encode(stream));
    }
}
