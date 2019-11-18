package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.nas.impl.values.VPartialTrackingAreaIdentityList;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

import java.util.ArrayList;
import java.util.List;

public class IE5gsTrackingAreaIdentityList extends InformationElement4 {
    public List<VPartialTrackingAreaIdentityList> partialTrackingAreaIdentityLists;

    @Override
    protected IE5gsTrackingAreaIdentityList decodeIE4(OctetInputStream stream, int length) {
        var res = new IE5gsTrackingAreaIdentityList();
        res.partialTrackingAreaIdentityLists = new ArrayList<>();
        while (stream.hasNext()) {
            partialTrackingAreaIdentityLists.add(VPartialTrackingAreaIdentityList.decode(stream));
        }
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        for (var list : partialTrackingAreaIdentityLists) {
            list.encode(stream);
        }
    }
}
