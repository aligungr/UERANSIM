package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainMmMessage;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.ies.IE5gsIdentityType;

public class IdentityRequest extends PlainMmMessage {
    public IE5gsIdentityType identityType;

    public IdentityRequest() {
        super(EMessageType.IDENTITY_REQUEST);
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE1("identityType");
    }
}
