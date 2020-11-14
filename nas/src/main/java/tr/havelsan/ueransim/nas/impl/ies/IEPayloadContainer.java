/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement6;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class IEPayloadContainer extends InformationElement6 {
    public OctetString payload;

    public IEPayloadContainer() {
    }

    public IEPayloadContainer(OctetString payload) {
        this.payload = payload;
    }

    @Override
    protected IEPayloadContainer decodeIE6(OctetInputStream stream, int length) {
        var res = new IEPayloadContainer();
        res.payload = stream.readOctetString(length);
        return res;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        stream.writeOctetString(payload);
    }
}
