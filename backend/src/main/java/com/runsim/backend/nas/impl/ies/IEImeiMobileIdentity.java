package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IEImeiMobileIdentity extends IE5gsMobileIdentity {
    public String imei;

    @Override
    public IE5gsMobileIdentity decodeMobileIdentity(OctetInputStream stream, int length, boolean isEven) {
        var imeiMobileIdentity = new IEImeiMobileIdentity();
        imeiMobileIdentity.imei = NasDecoder.bcdString(stream, length, true);
        return imeiMobileIdentity;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        NasEncoder.bcdString(stream, imei, -1);
    }
}
