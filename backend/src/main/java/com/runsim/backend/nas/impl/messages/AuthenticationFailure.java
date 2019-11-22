package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainMmMessage;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.ies.IE5gMmCause;
import com.runsim.backend.nas.impl.ies.IEAuthenticationFailureParameter;

public class AuthenticationFailure extends PlainMmMessage {
    public IE5gMmCause mmCause;
    public IEAuthenticationFailureParameter authenticationFailureParameter;

    public AuthenticationFailure() {
        super(EMessageType.AUTHENTICATION_FAILURE);
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE("mmCause");
        builder.optionalIE(0x30, "authenticationFailureParameter");
    }
}
