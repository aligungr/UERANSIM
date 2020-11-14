/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement3;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class IEAuthenticationParameterRand extends InformationElement3 {
    public OctetString value;

    public IEAuthenticationParameterRand() {
    }

    public IEAuthenticationParameterRand(OctetString value) {
        this.value = value;
    }

    @Override
    protected InformationElement3 decodeIE3(OctetInputStream stream) {
        var res = new IEAuthenticationParameterRand();
        res.value = stream.readOctetString(16);
        return res;
    }

    @Override
    public void encodeIE3(OctetOutputStream stream) {
        stream.writeOctetString(value);
    }
}
