package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.Decoder;
import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.impl.ies.IE5gsMobileIdentity;
import com.runsim.backend.utils.OctetInputStream;

public class IdentityResponse extends PlainNasMessage {
    public IE5gsMobileIdentity mobileIdentity;

    @Override
    public IdentityResponse decodeMessage(OctetInputStream stream) {
        var resp = new IdentityResponse();
        resp.mobileIdentity = Decoder.mobileIdentity(stream, false);
        return resp;
    }
}
