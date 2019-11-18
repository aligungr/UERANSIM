package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.messages.PlainNasMessage;

public class DeRegistrationAcceptUeTerminated extends PlainNasMessage {
    // No information elements for this message type

    @Override
    public void transcode(ITranscodeBuilder builder) {
        super.transcode(builder);
    }
}
