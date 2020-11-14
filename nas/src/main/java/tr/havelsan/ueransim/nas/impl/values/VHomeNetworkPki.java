/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.values;

import tr.havelsan.ueransim.nas.core.NasValue;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.Octet;

public class VHomeNetworkPki extends NasValue {
    public Octet value;

    public VHomeNetworkPki() {
    }

    public VHomeNetworkPki(Octet value) {
        this.value = value;
    }

    public VHomeNetworkPki(int value) {
        this(new Octet(value));
    }

    public VHomeNetworkPki(String hex) {
        this(new Octet(hex));
    }

    @Override
    public VHomeNetworkPki decode(OctetInputStream stream) {
        var res = new VHomeNetworkPki();
        res.value = stream.readOctet();
        return res;
    }

    public boolean isReserved() {
        return value.intValue() == 0b11111111;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public void encode(OctetOutputStream stream) {
        stream.writeOctet(value);
    }
}
