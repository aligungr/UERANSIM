/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.values;

import tr.havelsan.ueransim.nas.core.NasValue;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.Octet;

public class VTimeZone extends NasValue {
    public Octet value;

    public VTimeZone() {
    }

    public static VTimeZone fromOctet(Octet octet) {
        var res = new VTimeZone();
        res.value = octet;
        return res;
    }

    public static VTimeZone fromOctet(int octet) {
        return fromOctet(new Octet(octet));
    }

    @Override
    public VTimeZone decode(OctetInputStream stream) {
        return fromOctet(stream.readOctet());
    }

    @Override
    public void encode(OctetOutputStream stream) {
        stream.writeOctet(value);
    }
}
