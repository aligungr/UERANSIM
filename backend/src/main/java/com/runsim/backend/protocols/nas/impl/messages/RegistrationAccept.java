package com.runsim.backend.protocols.nas.impl.messages;

import com.runsim.backend.protocols.core.OctetInputStream;
import com.runsim.backend.protocols.nas.messages.NasMessage;
import com.runsim.backend.protocols.nas.messages.PlainNasMessage;

public class RegistrationAccept extends PlainNasMessage {
    @Override
    public NasMessage decodeMessage(OctetInputStream stream) {
        var resp = new RegistrationAccept();
        // todo
        return resp;
    }
}
