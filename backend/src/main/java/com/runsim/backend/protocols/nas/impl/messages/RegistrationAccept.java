package com.runsim.backend.protocols.nas.impl.messages;

import com.runsim.backend.protocols.core.OctetInputStream;
import com.runsim.backend.protocols.nas.messages.PlainNasMessage;

public class RegistrationAccept extends PlainNasMessage {

    @Override
    public RegistrationAccept decodeMessage(OctetInputStream stream) {
        var resp = new RegistrationAccept();
        // todo
        return resp;
    }
}
