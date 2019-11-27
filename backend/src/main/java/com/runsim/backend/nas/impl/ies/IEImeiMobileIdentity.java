package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.bits.Bit4;

public class IEImeiMobileIdentity extends IE5gsMobileIdentity {
    public String imei;

    public static IE5gsMobileIdentity decodeMobileIdentity(OctetInputStream stream, int length, boolean isEven) {
        var imeiMobileIdentity = new IEImeiMobileIdentity();
        imeiMobileIdentity.imei = NasDecoder.bcdString(stream, length, true);
        return imeiMobileIdentity;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        int imeiFlag = 0b0011;
        if (imei.length() % 2 != 0)
            imeiFlag |= 0b1000; // odd flag set if imei has odd number of digits.

        NasEncoder.bcdString(stream, imei, -1, true, new Bit4(imeiFlag));
    }
}
