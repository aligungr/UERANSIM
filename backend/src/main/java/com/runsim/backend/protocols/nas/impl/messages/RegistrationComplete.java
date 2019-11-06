package com.runsim.backend.protocols.nas.impl.messages;

import com.runsim.backend.protocols.core.OctetInputStream;
import com.runsim.backend.protocols.nas.messages.PlainNasMessage;

public class RegistrationComplete extends PlainNasMessage {

    @Override
    public RegistrationComplete decodeMessage(OctetInputStream stream) {
        return new RegistrationComplete();
    }
}
