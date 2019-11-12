package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.impl.ies.IE5gsMobileIdentity;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;

public class IdentityResponse extends PlainNasMessage {
    public IE5gsMobileIdentity mobileIdentity;

    @Override
    public IdentityResponse decodeMessage(OctetInputStream stream) {
        var resp = new IdentityResponse();
        resp.mobileIdentity = NasDecoder.mobileIdentity(stream);
        return resp;
    }

    @Override
    public void encodeMessage(OctetOutputStream stream) {
        super.encodeMessage(stream);
        NasEncoder.ie2346(stream, mobileIdentity);
    }
}
