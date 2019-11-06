package com.runsim.backend.protocols.nas.impl.messages;

import com.runsim.backend.protocols.core.OctetInputStream;
import com.runsim.backend.protocols.nas.impl.ie.IE5gsMobileIdentity;
import com.runsim.backend.protocols.nas.messages.NasMessage;
import com.runsim.backend.protocols.nas.messages.PlainNasMessage;

public class IdentityResponse extends PlainNasMessage {
    public IE5gsMobileIdentity mobileIdentity;

    @Override
    public NasMessage decodeMessage(OctetInputStream stream) {
        var resp = new IdentityResponse();
        //resp.mobileIdentity = IdentityType.fromValue(hashCode()); todo
        return resp;
    }
}
