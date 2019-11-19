package com.runsim.backend.nas.impl.values;

import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.nas.impl.ies.IE5gsTrackingAreaIdentityList;
import com.runsim.backend.nas.impl.ies.IEDnn;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class VLadn extends NasValue {
    public IEDnn dnn;
    public IE5gsTrackingAreaIdentityList trackingAreaIdentityList;

    public static VLadn decode(OctetInputStream stream) {
        var res = new VLadn();
        res.dnn = NasDecoder.ie2346(stream, IEDnn.class);
        res.trackingAreaIdentityList = NasDecoder.ie2346(stream, IE5gsTrackingAreaIdentityList.class);
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        NasEncoder.ie2346(stream, dnn);
        NasEncoder.ie2346(stream, trackingAreaIdentityList);
    }
}
