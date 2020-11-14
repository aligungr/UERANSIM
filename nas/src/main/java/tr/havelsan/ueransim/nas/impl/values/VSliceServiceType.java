/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.values;

import tr.havelsan.ueransim.nas.core.NasValue;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.Octet;

public class VSliceServiceType extends NasValue {
    public Octet value;

    public VSliceServiceType() {
    }

    public VSliceServiceType(Octet value) {
        this.value = value;
    }

    public VSliceServiceType(int value) {
        this(new Octet(value));
    }

    public VSliceServiceType(String hex) {
        this(new Octet(hex));
    }

    @Override
    public VSliceServiceType decode(OctetInputStream stream) {
        var res = new VSliceServiceType();
        res.value = stream.readOctet();
        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        stream.writeOctet(value);
    }

    @Override
    public String toString() {
        return Integer.toString(value.intValue());
    }
}
