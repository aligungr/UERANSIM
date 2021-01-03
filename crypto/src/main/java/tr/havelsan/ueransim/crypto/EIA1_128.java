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

public class EIA1_128 {

    public static Octet4 computeMac(Octet4 count, Bit5 bearer, Bit direction, BitString message, OctetString key) {
        var freshBits = new BitString();
        for (int i = 0; i < 5; i++) {
            freshBits.set(i, bearer.getBitB(4 - i));
        }
        freshBits.clear(31);

        var octets = freshBits.toOctetArray();
        var fresh = new Octet4(octets[0], octets[1], octets[2], octets[3]);
        return UIA2.computeMac(count, fresh, direction, message, key);
    }
}
