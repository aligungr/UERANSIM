package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainMmMessage;
import com.runsim.backend.nas.impl.enums.EMessageType;

public class DeRegistrationAcceptUeTerminated extends PlainMmMessage {
    // No information elements for this message type

    public DeRegistrationAcceptUeTerminated() {
        super(EMessageType.DEREGISTRATION_ACCEPT_UE_TERMINATED);
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);
    }
}
