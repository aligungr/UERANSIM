/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.crypto;

import tr.havelsan.ueransim.utils.bits.Bit;
import tr.havelsan.ueransim.utils.bits.Bit5;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet4;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class NEA2_128 {

    public static BitString encrypt(Octet4 count, Bit5 bearer, Bit direction, BitString message, OctetString key) {
        return EEA2_128.encrypt(count, bearer, direction, message, key);
    }

    public static BitString decrypt(Octet4 count, Bit5 bearer, Bit direction, BitString message, OctetString key) {
        return EEA2_128.decrypt(count, bearer, direction, message, key);
    }
}
