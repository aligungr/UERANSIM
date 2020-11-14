/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.NasEncoder;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.bits.Bit4;

public class IEImeiSvMobileIdentity extends IE5gsMobileIdentity {
    public String imeiSv;

    public IEImeiSvMobileIdentity() {
    }

    public IEImeiSvMobileIdentity(String imeiSv) {
        this.imeiSv = imeiSv;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        int imeiSvFlag = 0b0101;
        if (imeiSv.length() % 2 != 0)
            imeiSvFlag |= 0b1000; // odd flag set if imeisv has odd number of digits.

        NasEncoder.bcdString(stream, imeiSv, -1, true, new Bit4(imeiSvFlag));
    }
}
