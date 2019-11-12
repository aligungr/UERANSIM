package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.impl.ies.IE5gsMobileIdentity;

public class IdentityResponse extends PlainNasMessage {
    public IE5gsMobileIdentity mobileIdentity;

    @Override
    public void transcode(ITranscodeBuilder builder) {
        super.transcode(builder);

        builder.mandatoryIE("mobileIdentity");
    }
}
