package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.nas.impl.values.VPartialServiceAreaList;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.Utils;

import java.util.List;

public class IEServiceAreaList extends InformationElement4 {
    public List<VPartialServiceAreaList> partialServiceAreaLists;

    @Override
    protected IEServiceAreaList decodeIE4(OctetInputStream stream, int length) {
        var res = new IEServiceAreaList();
        res.partialServiceAreaLists = Utils.decodeList(stream, VPartialServiceAreaList::decode, 0, length);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        partialServiceAreaLists.forEach(item -> item.encode(stream));
    }
}
