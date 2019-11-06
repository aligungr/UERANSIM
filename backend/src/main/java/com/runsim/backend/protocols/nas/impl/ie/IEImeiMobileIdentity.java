package com.runsim.backend.protocols.nas.impl.ie;

import com.runsim.backend.protocols.core.OctetInputStream;
import com.runsim.backend.protocols.nas.DecodeUtils;

public class IEImeiMobileIdentity extends IE5gsMobileIdentity {
    public String imei;

    public IEImeiMobileIdentity decodeIMEI(OctetInputStream stream, int length, boolean isEven) {
        var imeiMobileIdentity = new IEImeiMobileIdentity();
        imeiMobileIdentity.imei = DecodeUtils.decodeBCDString(stream, length, true);
        return imeiMobileIdentity;
    }
}
