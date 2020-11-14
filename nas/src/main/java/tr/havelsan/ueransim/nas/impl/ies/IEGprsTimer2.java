/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.Octet;

public class IEGprsTimer2 extends InformationElement4 {
    public Octet value;

    public IEGprsTimer2() {
    }

    public IEGprsTimer2(Octet value) {
        this.value = value;
    }

    public IEGprsTimer2(int value) {
        this(new Octet(value));
    }

    public boolean hasValue() {
        return value.intValue() != 0;
    }

    @Override
    protected IEGprsTimer2 decodeIE4(OctetInputStream stream, int length) {
        var res = new IEGprsTimer2();
        res.value = stream.readOctet();
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctet(value);
    }
}
