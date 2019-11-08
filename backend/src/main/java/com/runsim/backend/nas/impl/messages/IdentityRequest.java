package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.impl.enums.EIdentityType;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IdentityRequest extends PlainNasMessage {
    public EIdentityType identityType;

    @Override
    public IdentityRequest decodeMessage(OctetInputStream stream) {
        var req = new IdentityRequest();
        var flags = stream.readOctetI();
        req.identityType = EIdentityType.fromValue(flags & 0b111);
        return req;
    }

    @Override
    public void encodeMessage(OctetOutputStream stream) {
        super.encodeMessage(stream);
        stream.writeOctet(identityType.value);
    }
}
