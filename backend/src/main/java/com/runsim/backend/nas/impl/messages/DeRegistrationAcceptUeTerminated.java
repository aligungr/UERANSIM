package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainMmMessage;

public class DeRegistrationAcceptUeTerminated extends PlainMmMessage {
    // No information elements for this message type

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);
    }
}
