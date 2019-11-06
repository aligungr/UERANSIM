package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.impl.enums.EIdentityType;
import com.runsim.backend.utils.OctetInputStream;

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
