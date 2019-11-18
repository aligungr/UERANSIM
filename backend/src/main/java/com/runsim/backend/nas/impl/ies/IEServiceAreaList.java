package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.nas.impl.values.VServiceAreaList;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

import java.util.ArrayList;
import java.util.List;

public class IEServiceAreaList extends InformationElement4 {
    public List<VServiceAreaList> partialServiceAreaLists;

    @Override
    protected IEServiceAreaList decodeIE4(OctetInputStream stream, int length) {
        var res = new IEServiceAreaList();
        res.partialServiceAreaLists = new ArrayList<>();
        while (stream.hasNext()) {
            partialServiceAreaLists.add(VServiceAreaList.decode(stream));
        }
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        for (var list : partialServiceAreaLists) {
            list.encode(stream);
        }
    }
}
