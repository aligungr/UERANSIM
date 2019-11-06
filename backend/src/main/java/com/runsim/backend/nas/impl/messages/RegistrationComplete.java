package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.utils.OctetInputStream;

public class RegistrationComplete extends PlainNasMessage {

    @Override
    public RegistrationComplete decodeMessage(OctetInputStream stream) {
        var res = new RegistrationComplete();
        // todo
        return res;
    }
}
