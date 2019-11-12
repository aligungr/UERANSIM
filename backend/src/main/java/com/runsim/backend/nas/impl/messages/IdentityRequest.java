package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.impl.ies.IE5gsIdentityType;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.bits.Bit4;

public class IdentityRequest extends PlainNasMessage {
    public IE5gsIdentityType identityType;

    @Override
    public IdentityRequest decodeMessage(OctetInputStream stream) {
        var req = new IdentityRequest();
        req.identityType = NasDecoder.ie1(stream.readOctetI() & 0xF, IE5gsIdentityType.class);
        return req;
    }

    @Override
    public void encodeMessage(OctetOutputStream stream) {
        super.encodeMessage(stream);
        NasEncoder.ie1(stream, new Bit4(0), identityType);
    }
}
