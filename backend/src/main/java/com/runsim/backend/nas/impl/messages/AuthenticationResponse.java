package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainMmMessage;
import com.runsim.backend.nas.impl.ies.IEAuthenticationResponseParameter;
import com.runsim.backend.nas.impl.ies.IEEapMessage;

public class AuthenticationResponse extends PlainMmMessage {
    public IEAuthenticationResponseParameter authenticationResponseParameter;
    public IEEapMessage eapMessage;

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.optionalIE(0x2D, "authenticationResponseParameter");
        builder.optionalIE(0x78, "eapMessage");
    }
}
