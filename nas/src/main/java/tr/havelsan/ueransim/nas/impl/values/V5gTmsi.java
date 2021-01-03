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
import tr.havelsan.ueransim.utils.octets.Octet4;

public class V5gTmsi extends NasValue {
    public Octet4 value;

    public V5gTmsi() {
    }

    public V5gTmsi(Octet4 value) {
        this.value = value;
    }

    public V5gTmsi(long value) {
        this(new Octet4(value));
    }

    public V5gTmsi(String hex) {
        this(new Octet4(hex));
    }

    @Override
    public V5gTmsi decode(OctetInputStream stream) {
        Octet octet3 = stream.readOctet();
        Octet octet2 = stream.readOctet();
        Octet octet1 = stream.readOctet();
        Octet octet0 = stream.readOctet();

        var res = new V5gTmsi();
        res.value = new Octet4(octet3, octet2, octet1, octet0);
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        stream.writeOctet4(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
