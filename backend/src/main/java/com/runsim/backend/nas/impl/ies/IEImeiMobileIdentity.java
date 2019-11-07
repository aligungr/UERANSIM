package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.nas.Decoder;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IEImeiMobileIdentity extends IE5gsMobileIdentity {
    public String imei;

    @Override
    public IE5gsMobileIdentity decodeMobileIdentity(OctetInputStream stream, int length, boolean isEven) {
        var imeiMobileIdentity = new IEImeiMobileIdentity();
        imeiMobileIdentity.imei = Decoder.bcdString(stream, length, true);
        return imeiMobileIdentity;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        throw new NotImplementedException("");
    }
}
