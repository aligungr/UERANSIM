package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.nas.core.ies.InformationElement6;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.Utils;

import java.util.List;

public class IELadnInformation extends InformationElement6 {
    public List<VLadn> ladns;

    @Override
    protected IELadnInformation decodeIE6(OctetInputStream stream, int length) {
        var res = new IELadnInformation();
        res.ladns = Utils.decodeList(stream, VLadn::decode, length);
        return res;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        ladns.forEach(ladn -> ladn.encode(stream));
    }

    public static class VLadn extends NasValue {
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
}
