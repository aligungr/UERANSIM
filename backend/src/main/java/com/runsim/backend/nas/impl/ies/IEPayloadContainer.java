package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ies.InformationElement6;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.OctetString;

// TODO: BU ve diğer tüm yarı implement edilmiş olan octetStringli octetli olanlara bakılsın
public class IEPayloadContainer extends InformationElement6 {
    public OctetString payload;

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
