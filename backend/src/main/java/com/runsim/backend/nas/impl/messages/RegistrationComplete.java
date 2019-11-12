package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.impl.ies.IESorTransparentContainer;

public class RegistrationComplete extends PlainNasMessage {

    public IESorTransparentContainer sorTransparentContainer;

    @Override
    public void transcode(ITranscodeBuilder builder) {
        super.transcode(builder);

        builder.optionalIE(0x73, "sorTransparentContainer");
    }
}
