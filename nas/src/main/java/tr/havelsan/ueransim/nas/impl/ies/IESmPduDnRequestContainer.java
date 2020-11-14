/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;

import java.nio.charset.StandardCharsets;

public class IESmPduDnRequestContainer extends InformationElement4 {
    public String dnSpecificIdentity;

    public IESmPduDnRequestContainer() {
    }

    public IESmPduDnRequestContainer(String dnSpecificIdentity) {
        this.dnSpecificIdentity = dnSpecificIdentity;
    }

    @Override
    protected IESmPduDnRequestContainer decodeIE4(OctetInputStream stream, int length) {
        var bytes = stream.readOctetArrayB(length);
        var utf8 = new String(bytes, StandardCharsets.UTF_8);

        var res = new IESmPduDnRequestContainer();
        res.dnSpecificIdentity = utf8;
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        var bytes = dnSpecificIdentity.getBytes(StandardCharsets.UTF_8);
        stream.writeOctets(bytes);
    }
}
