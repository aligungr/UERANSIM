package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.utils.OctetInputStream;

public class AuthenticationResult extends PlainNasMessage {

    @Override
    public AuthenticationRequest decodeMessage(OctetInputStream stream) {
        throw new NotImplementedException("AuthenticationResult not implemented yet.");
    }
}
