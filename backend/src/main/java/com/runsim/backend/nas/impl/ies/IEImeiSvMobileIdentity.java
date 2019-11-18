package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.bits.Bit4;

public class IEImeiSvMobileIdentity extends IE5gsMobileIdentity {
    public String imeiSv;

    @Override
    public IEImeiSvMobileIdentity decodeMobileIdentity(OctetInputStream stream, int length, boolean isEven) {
        var res = new IEImeiSvMobileIdentity();
        res.imeiSv = NasDecoder.bcdString(stream, length, true);
        return res;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        int imeiSvFlag = 0b0101;
        if (imeiSv.length() % 2 != 0)
            imeiSvFlag |= 0b1000; // odd flag set if imeisv has odd number of digits.

        NasEncoder.bcdString(stream, imeiSv, -1, true, new Bit4(imeiSvFlag));
    }
}
