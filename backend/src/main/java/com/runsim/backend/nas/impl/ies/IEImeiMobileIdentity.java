package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.ProtocolDecoder;
import com.runsim.backend.utils.OctetInputStream;

public class IEImeiMobileIdentity extends IE5gsMobileIdentity {
    public String imei;

    public IEImeiMobileIdentity decodeIMEI(OctetInputStream stream, int length, boolean isEven) {
        var imeiMobileIdentity = new IEImeiMobileIdentity();
        imeiMobileIdentity.imei = ProtocolDecoder.bcdString(stream, length, true);
        return imeiMobileIdentity;
    }
}
