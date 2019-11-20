package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainMmMessage;
import com.runsim.backend.nas.impl.ies.IE5gsMobileIdentity;

public class IdentityResponse extends PlainMmMessage {
    public IE5gsMobileIdentity mobileIdentity;

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE("mobileIdentity");
    }
}
