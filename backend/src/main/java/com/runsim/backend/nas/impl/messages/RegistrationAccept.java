package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.utils.OctetInputStream;

public class RegistrationAccept extends PlainNasMessage {

    @Override
    public RegistrationAccept decodeMessage(OctetInputStream stream) {
        var resp = new RegistrationAccept();
        // todo
        return resp;
    }
}
