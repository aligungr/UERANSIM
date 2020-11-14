/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.NasEncoder;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.bits.Bit4;

public class IEImeiMobileIdentity extends IE5gsMobileIdentity {
    public String imei;

    public IEImeiMobileIdentity() {
    }

    public IEImeiMobileIdentity(String imei) {
        this.imei = imei;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        int imeiFlag = 0b0011;
        if (imei.length() % 2 != 0)
            imeiFlag |= 0b1000; // odd flag set if imei has odd number of digits.

        NasEncoder.bcdString(stream, imei, -1, true, new Bit4(imeiFlag));
    }
}
