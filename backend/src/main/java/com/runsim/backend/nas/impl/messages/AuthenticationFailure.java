package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.impl.ies.IE5gMmCause;
import com.runsim.backend.nas.impl.ies.IEAuthenticationFailureParameter;

public class AuthenticationFailure extends PlainNasMessage {

    public IE5gMmCause mmCause;
    public IEAuthenticationFailureParameter authenticationFailureParameter;

    @Override
    public void transcode(ITranscodeBuilder builder) {
        super.transcode(builder);

        builder.mandatoryIE("mmCause");
        builder.optionalIE(0x30, "authenticationFailureParameter");
    }
}
