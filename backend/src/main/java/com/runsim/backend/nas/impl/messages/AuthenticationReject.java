package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.impl.ies.IEEapMessage;

public class AuthenticationReject extends PlainNasMessage {
    public IEEapMessage eapMessage;

    @Override
    public void transcode(ITranscodeBuilder builder) {
        super.transcode(builder);

        builder.optionalIE(0x78, "eapMessage");
    }
}
