/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class IEAuthenticationParameterAutn extends InformationElement4 {
    public OctetString value;

    public IEAuthenticationParameterAutn() {
    }

    public IEAuthenticationParameterAutn(OctetString value) {
        this.value = value;
    }

    @Override
    protected InformationElement4 decodeIE4(OctetInputStream stream, int length) {
        var res = new IEAuthenticationParameterAutn();
        res.value = stream.readOctetString(length);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctetString(value);
    }
}
