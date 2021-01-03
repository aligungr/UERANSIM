/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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
