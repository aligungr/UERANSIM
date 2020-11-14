/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement6;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class IEEpsNasMessageContainer extends InformationElement6 {
    public OctetString value;

    public IEEpsNasMessageContainer() {
    }

    public IEEpsNasMessageContainer(OctetString value) {
        this.value = value;
    }

    @Override
    protected IEEpsNasMessageContainer decodeIE6(OctetInputStream stream, int length) {
        var res = new IEEpsNasMessageContainer();
        res.value = stream.readOctetString(length);
        return res;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        stream.writeOctetString(value);
    }
}
