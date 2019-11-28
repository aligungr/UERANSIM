package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.nas.impl.values.VPartialTrackingAreaIdentityList;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.Utils;

import java.util.List;

public class IE5gsTrackingAreaIdentityList extends InformationElement4 {
    public List<VPartialTrackingAreaIdentityList> partialTrackingAreaIdentityLists;

    @Override
    protected IE5gsTrackingAreaIdentityList decodeIE4(OctetInputStream stream, int length) {
        var res = new IE5gsTrackingAreaIdentityList();
        res.partialTrackingAreaIdentityLists = Utils.decodeList(stream, VPartialTrackingAreaIdentityList::decode, length);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        partialTrackingAreaIdentityLists.forEach(item -> item.encode(stream));
    }
}
