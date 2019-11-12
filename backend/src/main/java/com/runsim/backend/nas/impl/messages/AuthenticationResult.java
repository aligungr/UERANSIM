package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.impl.ies.IEAbba;
import com.runsim.backend.nas.impl.ies.IEEapMessage;
import com.runsim.backend.nas.impl.ies.IENasKeySetIdentifier;

public class AuthenticationResult extends PlainNasMessage {
    public IENasKeySetIdentifier ngKSI;
    public IEEapMessage eapMessage;
    public IEAbba abba;

    @Override
    public void transcode(ITranscodeBuilder builder) {
        super.transcode(builder);

        builder.mandatoryIE1("ngKSI", null);
        builder.mandatoryIE("eapMessage");
        builder.optionalIE(0x38, "abba");
    }
}
