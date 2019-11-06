package com.runsim.backend.protocols.nas.impl.messages;

import com.runsim.backend.protocols.core.OctetInputStream;
import com.runsim.backend.protocols.nas.impl.enums.EIdentityType;
import com.runsim.backend.protocols.nas.messages.PlainNasMessage;

public class IdentityRequest extends PlainNasMessage {
    public EIdentityType identityType;

    @Override
    public IdentityRequest decodeMessage(OctetInputStream stream) {
        var req = new IdentityRequest();
        var flags = stream.readOctetI();
        req.identityType = EIdentityType.fromValue(flags & 0b111);
        return req;
    }
}
