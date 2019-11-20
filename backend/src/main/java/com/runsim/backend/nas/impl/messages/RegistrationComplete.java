package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainMmMessage;
import com.runsim.backend.nas.impl.ies.IESorTransparentContainer;

public class RegistrationComplete extends PlainMmMessage {
    public IESorTransparentContainer sorTransparentContainer;

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.optionalIE(0x73, "sorTransparentContainer");
    }
}
