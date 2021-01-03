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
import tr.havelsan.ueransim.utils.octets.Octet3;

public class VSliceDifferentiator extends NasValue {
    public Octet3 value;

    public VSliceDifferentiator() {
    }

    public VSliceDifferentiator(Octet3 value) {
        this.value = value;
    }

    public VSliceDifferentiator(int value) {
        this(new Octet3(value));
    }

    public VSliceDifferentiator(String hex) {
        this(new Octet3(hex));
    }

    @Override
    public VSliceDifferentiator decode(OctetInputStream stream) {
        var res = new VSliceDifferentiator();
        res.value = stream.readOctet3();
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        stream.writeOctet3(value);
    }

    @Override
    public String toString() {
        return Integer.toString(value.intValue());
    }
}
