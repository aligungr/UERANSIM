package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainSmMessage;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.ies.IE5gSmCause;

public class FiveGSmStatus extends PlainSmMessage {
    public IE5gSmCause smCause;

    public FiveGSmStatus() {
        super(EMessageType.FIVEG_SM_STATUS);
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE("smCause");
    }
}
