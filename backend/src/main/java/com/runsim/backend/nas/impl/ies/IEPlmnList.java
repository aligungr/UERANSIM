package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.nas.impl.values.VPlmn;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.Utils;

import java.util.Arrays;

public class IEPlmnList extends InformationElement4 {
    public VPlmn[] plmns;

    public IEPlmnList() {
    }

    public IEPlmnList(VPlmn[] plmns) {
        this.plmns = plmns;
    }

    @Override
    protected InformationElement4 decodeIE4(OctetInputStream stream, int length) {
        var res = new IEPlmnList();
        res.plmns = Utils.decodeList(stream, new VPlmn()::decode, length, VPlmn.class);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        Arrays.stream(plmns).forEach(vMccMnc -> vMccMnc.encode(stream));
    }
}
